package com.bookstore

class Author {

    String country
    String name

    static hasMany = [books: Book]

}
