package com.bookstore

class Employees {

    String empId
    String name
    Integer age
    String deptName
    String address

    static belongsTo = [company: Company]
    static constraints = {
        age min: 18
    }

    String toString() {
        "$name(${empId})"
    }
}
