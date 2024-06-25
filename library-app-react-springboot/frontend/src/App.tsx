import "./App.css";
import { Footer } from "./layout/Footer";
import { Homepage } from "./layout/HomePage/Homepage";
import Navbar from "./layout/Navbar";
import { SearchBookPage } from "./layout/searchBookPage/SearchBookPage";

function App() {
  return (
    <div>
      <Navbar />
      {/* <Homepage/> */}
      <SearchBookPage/>
      <Footer />
    </div>
  );
}

export default App;
