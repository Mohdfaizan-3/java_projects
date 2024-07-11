import { useEffect, useState } from "react";
import BookModel from "../../../models/BookModel";
import { ReturnBook } from "./ReturnBook";
import { Link } from "react-router-dom";

export const Carousel = () => {
  const [books, setBooks] = useState<BookModel[]>([]);
  const [loading, setLoading] = useState(true);
  const [httpError, setHttpError] = useState(null);

  useEffect(() => {
    const fetchBooks = async () => {
      const baseUrl: string = "http://localhost:8080/api/books";
      const url: string = `${baseUrl}?page=0&size=9`;
      const response = await fetch(url);
      if (!response.ok) {
        throw new Error("something went wrong");
      }
      const responseJson = await response.json();
      const data = await responseJson._embedded.books;
      const loadedBooks: BookModel[] = [];
      for (const key in data) {
        loadedBooks.push({
          id: data[key].id,
          title: data[key].title,
          author: data[key].author,
          description: data[key].description,
          copies: data[key].copies,
          copiesAvailable: data[key].copiesAvailable,
          category: data[key].category,
          img: data[key].img,
        });
      }
      setBooks(loadedBooks);
      setLoading(false);
    };
    fetchBooks().catch((error: any) => {
      setLoading(false);
      setHttpError(error.message);
    });
  }, []);

  if (loading) {
    return <div className="container m-5">Loading....</div>;
  }

  if (httpError) {
    return <div className="container m-5">{httpError}</div>;
  }

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
              {books.slice(0, 3).map((book) => (
                <ReturnBook book={book} key={book.id} />
              ))}
            </div>
          </div>
          <div className="carousel-item">
            <div className="row d-flex justify-content-center align-items-center">
              {books.slice(3, 6).map((book) => (
                <ReturnBook book={book} key={book.id} />
              ))}
            </div>
          </div>
          <div className="carousel-item">
            <div className="row d-flex justify-content-center align-items-center">
              {books.slice(6, 9).map((book) => (
                <ReturnBook book={book} key={book.id} />
              ))}
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
          <ReturnBook book={books[7]} key={books[7].id} />
        </div>
      </div>
      <div className="homepage-carousel-title mt-3">
        <Link to="/search" className="btn btn-outline-secondary btn-lg">
          view more
        </Link>
      </div>
    </div>
  );
};
