package com.bookstore

class Company {

    String name
    String country
    List<Employees> employees

    static constraints = {
        employees nullable: true
    }

    String toString() {
        "$name(${country})"
    }
}
