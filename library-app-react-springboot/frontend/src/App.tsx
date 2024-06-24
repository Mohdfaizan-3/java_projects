import "./App.css";
import { Footer } from "./layout/Footer";
import { Homepage } from "./layout/HomePage/Homepage";
import Navbar from "./layout/Navbar";

function App() {
  return (
    <div>
      <Navbar />
      <Homepage/>
      <Footer />
    </div>
  );
}

export default App;
