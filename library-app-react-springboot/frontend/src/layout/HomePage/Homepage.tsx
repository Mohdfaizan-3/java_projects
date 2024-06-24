import { Carousel } from "./component/Carousel";
import { ExploreBook } from "./component/ExploreBook";
import { Heroes } from "./component/Heroes";
import { LibraryServices } from "./component/LibraryServices";

export const Homepage = () => {
  return (
    <>
      <ExploreBook />
      <Carousel />
      <Heroes />
      <LibraryServices />
    </>
  );
};
