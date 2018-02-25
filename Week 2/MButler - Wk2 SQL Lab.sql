--Matthew Butler
--Week 2 SQL LAB

-- Q1 --
-- Set up Oracle Chinook.
-- Done.

    -- Q2: SQL Queries--
-- 2.1 : SELECT
-- Task – Select all records from Employee Table
SELECT * FROM EMPLOYEE;

-- Task – Select all records from the Employee table where last name is King.
SELECT * FROM EMPLOYEE WHERE LASTNAME = 'King';

-- Task – Select all records from the Employee table where first name is Andrew and REPORTSTO is NULL.
SELECT * FROM EMPLOYEE WHERE FIRSTNAME = 'Andrew' AND REPORTSTO IS NULL;

-- 2.2 ORDER BY
-- Task – Select all albums in Album table and sort result set in descending order by title.
SELECT TITLE FROM ALBUM ORDER BY TITLE DESC;

-- Task – Select first name from Customer and sort result set in ascending order by city
SELECT FIRSTNAME FROM CUSTOMER ORDER BY CITY;
-- SELECT FIRSTNAME, CITY FROM CUSTOMER ORDER BY CITY; -- check to see if true

-- 2.3 INSERT INTO
-- Task – Insert two new records into Genre table 
INSERT INTO GENRE (GENREID, NAME) VALUES (48, 'Elevator');
INSERT INTO GENRE (GENREID, NAME) VALUES (72, 'White Noise');

-- Task – Insert two new records into Employee table
INSERT INTO EMPLOYEE (EMPLOYEEID, LASTNAME, FIRSTNAME, TITLE, REPORTSTO, BIRTHDATE, HIREDATE,
    ADDRESS, CITY, STATE, COUNTRY, POSTALCODE, PHONE, FAX, EMAIL) VALUES (9, 'Test', 
    'Johnny', 'Security', 1, '01-APR-98', '21-FEB-18', '1600 Pencil Way', 'Hot Coffee',
    'MS', 'USA', '55555', '555-555-5555', '555-555-5555', 'DNE@DoesNotExist.com');
INSERT INTO EMPLOYEE(EMPLOYEEID, LASTNAME, FIRSTNAME, TITLE) VALUES (23, 'Micheal', 'Jordan',
    'Spokesman');

-- Task – Insert two new records into Customer table
INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, CITY, STATE, COUNTRY, PHONE, EMAIL)
    VALUES (433, 'Tim', 'Bob', 'Star', 'MS', 'USA', '444-444-44444','Timbobb@Bobtim.net' );
INSERT INTO CUSTOMER (CUSTOMERID, FIRSTNAME, LASTNAME, CITY, STATE, COUNTRY, PHONE, EMAIL) VALUES (666, 'Lucifer',
    'Morningstar', 'Hades', 'Texas', 'USA', '666-666-6666', 'theApocalypseComes@mylittlepony.com');
    
-- 2.4 UPDATE
-- Task – Update Aaron Mitchell in Customer table to Robert Walter
UPDATE CUSTOMER SET FIRSTNAME = 'Robert', LASTNAME = 'Walter' 
    WHERE FIRSTNAME = 'Aaron' AND LASTNAME = 'Mitchell';

-- Task – Update name of artist in the Artist table “Creedence Clearwater Revival” to “CCR”	
UPDATE ARTIST SET NAME = 'CCR' WHERE NAME = 'Creedence Clearwater Revival';

-- 2.5 LIKE
-- Task – Select all invoices with a billing address like “T%” 
SELECT * FROM INVOICE WHERE BILLINGADDRESS LIKE 'T%';

-- 2.6 BETWEEN
-- Task – Select all invoices that have a total between 15 and 50
SELECT * FROM INVOICE WHERE TOTAL BETWEEN 15 AND 50;

-- Task – Select all employees hired between 1st of June 2003 and 1st of March 2004
SELECT * FROM EMPLOYEE WHERE HIREDATE BETWEEN TO_DATE ('01-Jun-2003', 'dd-Mon-yyyy')
    AND TO_DATE ('01-Mar-2004', 'dd-Mon-yyyy');
    
-- 2.7 DELETE
-- Task – Delete a record in Customer table where the name is Robert Walter 
-- (There may be constraints that rely on this, find out how to resolve them).

  --Track down Robert's constraints in INVOICE and INVOICELINE.
SELECT INVOICEID, CUSTOMERID FROM INVOICE WHERE CUSTOMERID = (SELECT CUSTOMERID FROM CUSTOMER WHERE 
    FIRSTNAME = 'Robert' AND LASTNAME = 'Walter');

  --Delete all INVOICELINE rows where the INVOICEID in INVOICE matches Robert's CUSTOMERID in CUSTOMERS
DELETE FROM INVOICELINE WHERE INVOICEID IN (SELECT INVOICEID FROM INVOICE WHERE CUSTOMERID = 
    (SELECT CUSTOMERID FROM CUSTOMER WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter'));

  --Delete all INVOICE rows where the CUSTOMERID matches Robert's CUSTOMERID
DELETE FROM INVOICE WHERE CUSTOMERID IN (SELECT CUSTOMERID FROM CUSTOMER WHERE FIRSTNAME = 'Robert' 
    AND LASTNAME = 'Walter');

  --Delete Robert Walter
DELETE FROM CUSTOMER WHERE FIRSTNAME = 'Robert' AND LASTNAME = 'Walter';


    -- Q3 SQL Functions
-- 3.1 System Defined Functions
-- Task – Create a function that returns the current time.
SELECT TO_CHAR
    (SYSDATE, 'MM-DD-YYYY HH24:MI:SS') "NOW"
     FROM DUAL;

-- Task – create a function that returns the length of name in MEDIATYPE table
SELECT LENGTH(NAME) FROM MEDIATYPE;

-- 3.2 System Defined Aggregate Functions
-- Task – Create a function that returns the average total of all invoices 
SELECT AVG(TOTAL) FROM INVOICE;

-- Task – Create a function that returns the most expensive track
SELECT MAX(UNITPRICE) FROM TRACK;

-- 3.3 User Defined Scalar Functions
-- Task – Create a function that returns the average price of invoiceline items (for each invoice) in the invoiceline table
CREATE OR REPLACE FUNCTION AVGINVOICELINE
    (INV_NUM NUMBER)
    RETURN NUMBER 
    AS AVG_NUM NUMBER (11,2);
    BEGIN
        SELECT AVG(DISTINCT UNITPRICE)
        INTO AVG_NUM
        FROM INVOICELINE 
        WHERE INVOICEID = INV_NUM;
        RETURN AVG_NUM;
    END;
/

SELECT DISTINCT INVOICEID, AVGINVOICELINE(INVOICEID) FROM INVOICELINE ORDER BY INVOICEID;


-- 3.4 User Defined Table Valued Functions
-- Task – Create a function that returns all employees who are born after 1968.
CREATE OR REPLACE FUNCTION BORNAFTER1968
    RETURN STRING IS EMP_NAME STRING(20);
    CURSOR EMP_CURS IS 
        SELECT FIRSTNAME FROM EMPLOYEE WHERE EXTRACT(YEAR FROM BIRTHDATE) > 1968;
    BEGIN        
        OPEN EMP_CURS;
        FETCH EMP_CURS INTO EMP_NAME;
        CLOSE EMP_CURS;
        RETURN EMP_NAME;
    END;
/
SELECT BORNAFTER1968, BIRTHDATE FROM EMPLOYEE;

--can get the output without a function. put it here just in case i forget to come back to it.
SELECT FIRSTNAME, BIRTHDATE FROM EMPLOYEE WHERE EXTRACT(YEAR FROM BIRTHDATE) > 1968;
            

    --   4.0 Stored Procedures
    -- In this section you will be creating and executing stored procedures. You will be creating various types of stored procedures
    --   that take input and output parameters.

-- 4.1 Basic Stored Procedure
-- Task – Create a stored procedure that selects the first and last names of all the employees.
CREATE OR REPLACE PROCEDURE EMPLOYEENAMES
    (P_FIRNAME OUT STRING, P_LASNAME OUT STRING)
    AS
    BEGIN
        SELECT FIRSTNAME, LASTNAME INTO P_FIRNAME, P_LASNAME
        FROM EMPLOYEE;
    END;
    /

-- 4.2 Stored Procedure Input Parameters
-- Task – Create a stored procedure that updates the personal information of an employee.
CREATE OR REPLACE PROCEDURE UPDATEEMPLOYEE
    (E_FIRSTNAME STRING, E_LASTNAME STRING, E_ADR STRING, E_CITY STRING,
    E_COUNTRY STRING, E_POSTCODE STRING)
    AS
    BEGIN
        UPDATE EMPLOYEE SET FIRSTNAME = E_FIRSTNAME, LASTNAME = E_LASTNAME, ADDRESS = E_ADR, 
            CITY = E_CITY, COUNTRY = E_COUNTRY, POSTALCODE = E_POSTCODE; 
    END;
/

-- Task – Create a stored procedure that returns the managers of an employee.
CREATE OR REPLACE PROCEDURE FINDMANAGER
    (E_EMPNAME IN STRING, E_REPORTSTO OUT INT, E_MANAGERNAME OUT STRING)
    AS
    BEGIN
        SELECT REPORTSTO INTO E_REPORTSTO FROM EMPLOYEE WHERE FIRSTNAME = E_EMPNAME;
        SELECT FIRSTNAME INTO E_MANAGERNAME FROM EMPLOYEE WHERE EMPLOYEEID = E_REPORTSTO;
    END;
    /


-- 4.3 Stored Procedure Output Parameters
-- Task – Create a stored procedure that returns the name and company of a customer.
CREATE OR REPLACE PROCEDURE CUST_INFO
    (C_CUSTID IN INT, C_FIRST OUT STRING, C_COMP OUT STRING)
    AS
    BEGIN
        SELECT FIRSTNAME, COMPANY INTO C_FIRST, C_COMP FROM CUSTOMER 
            WHERE CUSTOMERID = C_CUSTID;
    END;
    /


    --   5.0 Transactions
    -- In this section you will be working with transactions. Transactions are usually nested within a stored procedure.

-- Task – Create a transaction that given a invoiceId will delete that invoice (There may be constraints that rely on this, find
--   out how to resolve them).
CREATE OR REPLACE PROCEDURE DEL_INVOICE
    (INV_ID IN INT)
    AS
    BEGIN
        DELETE FROM INVOICELINE WHERE (INVOICEID = INV_ID);
        DELETE FROM INVOICE WHERE(INVOICEID = INV_ID);
    
    COMMIT;
    END;
    /

-- Task – Create a transaction nested within a stored procedure that inserts a new record in the Customer table
CREATE OR REPLACE PROCEDURE NEW_CUSTOMER
    (C_ID IN INT, C_FIRST IN STRING, C_LAST IN STRING, C_COMP IN STRING, C_ADDRESS IN STRING, C_CITY IN STRING,
        C_STATE IN STRING, C_COUNTRY IN STRING, C_POSTAL IN STRING, C_PHONE IN STRING, C_FAX IN STRING,
        C_EMAIL IN STRING, C_SUPPORTREPID IN STRING)
    AS
    BEGIN
    INSERT INTO CUSTOMER VALUES(C_ID, C_FIRST, C_LAST , C_COMP, C_ADDRESS, C_CITY,
        C_STATE, C_COUNTRY, C_POSTAL, C_PHONE, C_FAX, C_EMAIL, C_SUPPORTREPID);
    
    COMMIT;         --from my understanding, Oracle implicitly starts a transaction block for every sql
    END;
    /

    --   6.0 Triggers
    -- In this section you will create various kinds of triggers that work when certain DML statements are executed on a table.

-- 6.1 AFTER/FOR
-- Task - Create an after insert trigger on the employee table fired after a new record is inserted into the table.
CREATE SEQUENCE INCEMPIDCOUNT
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 20;
    
CREATE OR REPLACE TRIGGER NEWEMPLOYEE 
    AFTER INSERT ON EMPLOYEE
    FOR EACH ROW
    BEGIN
        UPDATE EMPLOYEE SET EMPLOYEEID = INCEMPIDCOUNT.NEXTVAL;
    END;     
/
-- Task – Create an after update trigger on the album table that fires after a row is inserted in the table
CREATE SEQUENCE INCALBUMID
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 20;

CREATE OR REPLACE TRIGGER NEWALUBM
    AFTER UPDATE ON ALBUM
    FOR EACH ROW
    BEGIN
        UPDATE ALBUM SET ALBUMID = INCALBUMID.NEXTVAL;
    END;
/
-- Task – Create an after delete trigger on the customer table that fires after a row is deleted from the table.
CREATE SEQUENCE RENUMBERCUSTOMER
    MINVALUE 1
    START WITH 1
    INCREMENT BY 1
    CACHE 20;

CREATE OR REPLACE TRIGGER REMOVECUSTOMER
    AFTER DELETE ON CUSTOMER
    FOR EACH ROW
    BEGIN
        UPDATE CUSTOMER SET CUSTOMERID = REMOVECUSTOMER.NEXTVAL;
    END;

-- 7.0 JOINS
-- In this section you will be working with combining various tables through the use of joins. You will work with outer, 
--  inner, right, left, cross, and self joins.

-- 7.1 INNER
-- Task – Create an inner join that joins customers and orders and specifies the name of the customer and the invoiceId.
SELECT CUSTOMER.FIRSTNAME, INVOICE.INVOICEID FROM CUSTOMER
    INNER JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

-- 7.2 OUTER
-- Task – Create an outer join that joins the customer and invoice table, specifying the CustomerId, firstname, lastname, 
--   invoiceId, and total.
SELECT CUSTOMER.CUSTOMERID, CUSTOMER.FIRSTNAME, CUSTOMER.LASTNAME, INVOICE.INVOICEID, INVOICE.TOTAL FROM CUSTOMER
   FULL OUTER JOIN INVOICE ON CUSTOMER.CUSTOMERID = INVOICE.CUSTOMERID;

-- 7.3 RIGHT
-- Task – Create a right join that joins album and artist specifying artist name and title.
SELECT ALBUM.TITLE, ARTIST.NAME FROM ALBUM RIGHT JOIN ARTIST ON ALBUM.ARTISTID = ARTIST.ARTISTID;

-- 7.4 CROSS
--Task – Create a cross join that joins album and artist and sorts by artist name in ascending order.
SELECT ALBUM.TITLE, ARTIST.NAME FROM ALBUM CROSS JOIN ARTIST ORDER BY ARTIST.NAME ASC;

-- 7.5 SELF
-- Task – Perform a self-join on the employee table, joining on the reportsto column.
SELECT A.FIRSTNAME AS FIRSTNAME1, B.FIRSTNAME AS FIRSTNAME2, A.REPORTSTO FROM EMPLOYEE A, EMPLOYEE B
    WHERE A.REPORTSTO <> B.REPORTSTO;
