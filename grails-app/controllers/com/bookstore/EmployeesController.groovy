package com.bookstore

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class EmployeesController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Employees.list(params), model:[employeesCount: Employees.count()]
    }

    def show(Employees employees) {
        respond employees
    }

    def create() {
        respond new Employees(params)
    }

    @Transactional
    def save(Employees employees) {
        println params
        if (employees == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (employees.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond employees.errors, view:'create'
            return
        }

        employees.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'employees.label', default: 'Employees'), employees.id])
                redirect employees
            }
            '*' { respond employees, [status: CREATED] }
        }
    }

    def edit(Employees employees) {
        respond employees
    }

    @Transactional
    def update(Employees employees) {
        println params
        if (employees == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (employees.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond employees.errors, view:'edit'
            return
        }

        employees.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'employees.label', default: 'Employees'), employees.id])
                redirect employees
            }
            '*'{ respond employees, [status: OK] }
        }
    }

    @Transactional
    def delete(Employees employees) {

        if (employees == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        employees.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'employees.label', default: 'Employees'), employees.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'employees.label', default: 'Employees'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
