import { OktaAuth } from "@okta/okta-auth-js";
import { LoginCallback, Security } from "@okta/okta-react";
import { Route, Routes, useNavigate } from "react-router-dom";
import "./App.css";
import LoginWidget from "./auth/LoginWidget";
import { Checkout } from "./layout/checkout/Checkout";
import { Footer } from "./layout/Footer";
import { Homepage } from "./layout/HomePage/Homepage";
import Navbar from "./layout/Navbar";
import { SearchBookPage } from "./layout/searchBookPage/SearchBookPage";
import { OktaConfig } from "./lib/OktaConfig";

const oktaAuth = new OktaAuth(OktaConfig);
function App() {
  const navigate = useNavigate();

  const customAuthHandler = () => {
    navigate("/login");
  };

  const restoreOriginalUri = async (_oktaAuth: any, originalUri: any) => {
    navigate(originalUri || "/");
  };

  return (
    <div className="d-flex flex-column min-vh-100">
      <Security
        oktaAuth={oktaAuth}
        onAuthRequired={customAuthHandler} // Redirects to /login if authentication is required
        restoreOriginalUri={restoreOriginalUri} // Restores original URI after successful login
      >
        <Navbar />
        <div className="flex-grow-1">
          <Routes>
            <Route path="/" element={<Homepage />} />
            <Route path="/search" element={<SearchBookPage />} />
            <Route path="/checkout/:bookId" element={<Checkout />} />
            <Route path="/login" element={<LoginWidget config={OktaConfig} />} />            <Route path="/login/callback" element={<LoginCallback />} />
          </Routes>
        </div>
      </Security>
      <Footer />
    </div>
  );
}

export default App;
