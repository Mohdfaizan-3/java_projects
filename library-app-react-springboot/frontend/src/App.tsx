import "./App.css";
import { Carousel } from "./layout/HomePage/Carousel";
import { ExploreBook } from "./layout/HomePage/ExploreBook";
import Navbar from "./layout/Navbar";

function App() {
  return (
    <div>
      <Navbar />
      <ExploreBook />
      <Carousel/>
    </div>
  );
}

export default App;
