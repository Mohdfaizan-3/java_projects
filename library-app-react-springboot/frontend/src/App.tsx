import { Route, Routes } from "react-router-dom";
import "./App.css";
import { Footer } from "./layout/Footer";
import { Homepage } from "./layout/HomePage/Homepage";
import Navbar from "./layout/Navbar";
import { SearchBookPage } from "./layout/searchBookPage/SearchBookPage";
import { Checkout } from "./layout/checkout/Checkout";

function App() {
  return (
    <div className="d-flex flex-column min-vh-100">
      <Navbar />
      <div className="flex-grow-1">
        <Routes>
          <Route path="/" element={<Homepage />} />
          <Route path="/search" element={<SearchBookPage />} />
          <Route path="/checkout/:bookId" element={<Checkout/>}/>
        </Routes>
      </div>

      <Footer />
    </div>
  );
}

export default App;
