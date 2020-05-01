package com.bookstore

class Author {

    String country
    String name

    static hasMany = [books: Book]


    String toString(){
        "[name: $name, country: $country]"
    }
}
