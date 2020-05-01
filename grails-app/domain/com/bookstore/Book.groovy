package com.bookstore

class Book {

    String isbn
    String title
    String description
    String publisher
    Double price = 100
    Date publishDate
    Date dateCreated
    Date lastUpdated

    static belongsTo = [author:Author]

    static constraints = {
        description nullable: true
    }
}