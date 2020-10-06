package com.example.library;

import java.time.LocalDateTime;
import java.util.Date;

public class RentBody {

    private Integer id;
    private Integer bookId;
    private Integer userId;
    private LocalDateTime rentStart;
    private LocalDateTime RentEnd;

    public RentBody() {
    }

    public RentBody(int id, int bookId, int userId, LocalDateTime rentStart, LocalDateTime rentEnd) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.rentStart = rentStart;
        RentEnd = rentEnd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getRentStart() {
        return rentStart;
    }

    public void setRentStart(LocalDateTime rentStart) {
        this.rentStart = rentStart;
    }

    public LocalDateTime getRentEnd() {
        return RentEnd;
    }

    public void setRentEnd(LocalDateTime rentEnd) {
        RentEnd = rentEnd;
    }

    @Override
    public String toString() {
        return "RentBody{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", userId=" + userId +
                ", rentStart=" + rentStart +
                ", RentEnd=" + RentEnd +
                '}';
    }
}
