import { useEffect, useState } from "react";
import { ReturnBook } from "./ReturnBook";
import BookModel from "../../../models/BookModel";

export const Carousel = () => {

  const [books, setBooks] = useState<BookModel[]>([]);
  const [loading, setLoading] = useState(true);
  const [httpError, setHttpError] = useState(null);

  useEffect(()=>{
    const fetchBooks = async () => {
        const baseUrl: string = "http://localhost:8080/api/books";
        const url : string = `${baseUrl}?page=0&size=9`;
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error("something went wrong");
        } 
        const json = await response.json();
        const data = await json._embedded.books;
        const loadedBooks: BookModel[] = [];
        for (const key in data) {
            loadedBooks.push({
              id : data[key].title,
              title : data[key].title,
              author : data[key].author,
              description : data[key].description,
              copies : data[key].copies,
              copiesAvailable : data[key].copiesAvailable,
              category : data[key].category,
              img : data[key].img
            })
        }
        setBooks(loadedBooks);
        setLoading(false);

    }
    fetchBooks().catch((error: any)=>{
      setLoading(false);
      setHttpError(error.message);
    });
  }, []);
  return (
    <div className="container mt-5" style={{ height: 550 }}>
      <div className="homepage-carousel-title">
        <h3>Find your next book</h3>
      </div>
      <div
        id="carouselExampleControls"
        className="carousel carousel-dark slide mt-5 d-none d-lg-block"
        data-bs-interval="false"
      >
        <div className="carousel-inner">
          <div className="carousel-item active">
            <div className="row d-flex justify-content-center align-items-center">
              <ReturnBook />
              <ReturnBook />
              <ReturnBook />
            </div>
          </div>
          <div className="carousel-item">
            <div className="row d-flex justify-content-center align-items-center">
              <ReturnBook />

              <ReturnBook />
              <ReturnBook />
            </div>
          </div>
          <div className="carousel-item">
            <div className="row d-flex justify-content-center align-items-center">
              <ReturnBook />
              <ReturnBook />
              <ReturnBook />
            </div>
          </div>
        </div>
        <button
          className="carousel-control-next"
          type="button"
          data-bs-target="#carouselExampleControls"
          data-bs-slide="next"
        >
          <span
            className="carousel-control-next-icon"
            aria-hidden="true"
          ></span>
          <span className="visually-hidden">Next</span>
        </button>
      </div>
      {/* mobile */}
      <div className="d-lg-none mt-3">
        <div className="row d-flex justify-content-center align-items-center">
          <ReturnBook />
          <ReturnBook />
          <ReturnBook />
        </div>
      </div>
      <div className="homepage-carousel-title mt-3">
        <a className="btn btn-outline-secondary btn-lg" href="#">
          view more
        </a>
      </div>
    </div>
  );
};
