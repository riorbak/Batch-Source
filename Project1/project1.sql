CREATE TABLE ERS_REIMBURSEMENT_TYPE(
RT_ID       NUMBER(*,0) NOT NULL PRIMARY KEY,
RT_TYPE     VARCHAR2(30) NOT NULL);

CREATE TABLE ERS_REIMBURSEMENT_STATUS(
RS_ID       NUMBER(*,0) NOT NULL PRIMARY KEY,
RS_STATUS   VARCHAR2(30) NOT NULL);

CREATE TABLE ERS_USER_ROLES(
UR_ID       NUMBER(*,0) NOT NULL PRIMARY KEY,
UR_ROLE     VARCHAR2(40));

CREATE TABLE ERS_USERS(
U_ID        NUMBER(*,0) NOT NULL PRIMARY KEY,
U_USERNAME  VARCHAR2(40) NOT NULL UNIQUE,
U_PASSWORD  VARCHAR2(40) NOT NULL,
U_FIRSTNAME VARCHAR2(30),
U_LASTNAME  VARCHAR2(30),
U_EMAIL     VARCHAR2(100) UNIQUE,
UR_ID       NUMBER(*,0) NOT NULL,
CONSTRAINT FK_USER_ROLE FOREIGN KEY (UR_ID) REFERENCES ERS_USER_ROLES(UR_ID));

CREATE TABLE ERS_REIMBURSEMENTS(
R_ID            NUMBER(*,0) NOT NULL PRIMARY KEY,
R_AMOUNT        NUMBER(22,2) NOT NULL,
R_DESCRIPTION   VARCHAR2(100),
R_RECEIPT       BLOB,
R_SUBMITTED     TIMESTAMP NOT NULL,
R_RESOLVED      TIMESTAMP,
U_ID_AUTHOR     NUMBER(*,0) NOT NULL,
U_ID_RESOLVER   NUMBER(*,0),
RT_TYPE         NUMBER(*,0) NOT NULL,
RT_STATUS       NUMBER(*,0) NOT NULL,
CONSTRAINT FK_USER_AUTHOR FOREIGN KEY (U_ID_AUTHOR) REFERENCES ERS_USERS(U_ID),
CONSTRAINT FK_USER_RESOLVER FOREIGN KEY (U_ID_RESOLVER) REFERENCES ERS_USERS(U_ID),
CONSTRAINT FK_REQUEST_TYPE FOREIGN KEY (RT_TYPE) REFERENCES ERS_REIMBURSEMENT_TYPE(RT_ID),
CONSTRAINT FK_REQUEST_STATUS FOREIGN KEY (RT_STATUS) REFERENCES ERS_REIMBURSEMENT_STATUS(RS_ID));

--Create the user roles
INSERT INTO ERS_USER_ROLES(UR_ID, UR_ROLE) VALUES(1, 'Manager');
INSERT INTO ERS_USER_ROLES(UR_ID, UR_ROLE) VALUES(2, 'Employee');

--Create the reimbursement types
INSERT INTO ERS_REIMBURSEMENT_TYPE(RT_ID, RT_TYPE) VALUES (1, 'Travel');
INSERT INTO ERS_REIMBURSEMENT_TYPE(RT_ID, RT_TYPE) VALUES (2, 'Testing Certifications');
INSERT INTO ERS_REIMBURSEMENT_TYPE(RT_ID, RT_TYPE) VALUES (3, 'Morty Insurance');
INSERT INTO ERS_REIMBURSEMENT_TYPE(RT_ID, RT_TYPE) VALUES (4, 'Portal Gun Replacement');
INSERT INTO ERS_REIMBURSEMENT_TYPE(RT_ID, RT_TYPE) VALUES (5, 'Other');

--Create the reimbursement states
INSERT INTO ERS_REIMBURSEMENT_STATUS(RS_ID, RS_STATUS) VALUES (1, 'Pending');
INSERT INTO ERS_REIMBURSEMENT_STATUS(RS_ID, RS_STATUS) VALUES (2, 'Approved');
INSERT INTO ERS_REIMBURSEMENT_STATUS(RS_ID, RS_STATUS) VALUES (3, 'Denied');


--Create the employee User ID sequence
CREATE SEQUENCE U_ID_SEQUENCE
START WITH 1
INCREMENT BY 1;

--Create the reimbursement ID sequence
CREATE SEQUENCE R_ID_SEQUENCE
START WITH 1
INCREMENT BY 1;

--CREATION PROCEDURES
--Stored procedure for inserting user
CREATE OR REPLACE PROCEDURE ADD_USER(
    username IN ERS_USERS.U_USERNAME%TYPE,
    pass IN ERS_USERS.U_PASSWORD%TYPE,
    firstName IN ERS_USERS.U_FIRSTNAME%TYPE,
    lastName IN ERS_USERS.U_LASTNAME%TYPE,
    email IN ERS_USERS.U_EMAIL%TYPE,
    uRole IN ERS_USERS.UR_ID%TYPE)
    
IS
BEGIN
    INSERT INTO ERS_USERS("U_ID", "U_USERNAME", "U_PASSWORD", "U_FIRSTNAME", "U_LASTNAME", "U_EMAIL", "UR_ID")
    VALUES(U_ID_SEQUENCE.NEXTVAL, username, pass, firstName, lastName, email, uRole);
    
    COMMIT;
END;
/
--Create the procedure for adding Reimbursements
CREATE OR REPLACE PROCEDURE ADD_REIMBURSEMENT(
    amount IN ERS_REIMBURSEMENTS.R_AMOUNT%TYPE,
    r_desc IN ERS_REIMBURSEMENTS.R_DESCRIPTION%TYPE,
    receipt IN ERS_REIMBURSEMENTS.R_RECEIPT%TYPE,
    submitTime IN ERS_REIMBURSEMENTS.R_SUBMITTED%TYPE,
    resolvedTime IN ERS_REIMBURSEMENTS.R_RESOLVED%TYPE,
    r_author IN ERS_REIMBURSEMENTS.U_ID_AUTHOR%TYPE,
    r_resolver IN ERS_REIMBURSEMENTS.U_ID_RESOLVER%TYPE,
    r_type IN ERS_REIMBURSEMENTS.RT_TYPE%TYPE,
    r_status IN ERS_REIMBURSEMENTS.RT_STATUS%TYPE)
    
IS
BEGIN
    INSERT INTO ERS_REIMBURSEMENTS("R_ID", "R_AMOUNT", "R_DESCRIPTION", "R_RECEIPT", "R_SUBMITTED", "R_RESOLVED", "U_ID_AUTHOR", "U_ID_RESOLVER", "RT_TYPE", "RT_STATUS")
    VALUES(R_ID_SEQUENCE.NEXTVAL, amount, r_desc, receipt, submitTime, resolvedTime, r_author, r_resolver, r_type, r_status);
    
    COMMIT;
END;
/

--UPDATE PROCEDURES
--Update User
CREATE OR REPLACE PROCEDURE UPDATE_USER(
    us_id IN ERS_USERS.U_ID%TYPE,
    username IN ERS_USERS.U_USERNAME%TYPE,
    pass IN ERS_USERS.U_PASSWORD%TYPE,
    firstName IN ERS_USERS.U_FIRSTNAME%TYPE,
    lastName IN ERS_USERS.U_LASTNAME%TYPE,
    email IN ERS_USERS.U_EMAIL%TYPE,
    uRole IN ERS_USERS.UR_ID%TYPE)
    
IS
BEGIN
    UPDATE ERS_USERS SET U_USERNAME=username, U_PASSWORD=pass, U_FIRSTNAME=firstName, U_LASTNAME=lastName, U_EMAIL=email, UR_ID=uRole WHERE U_ID=us_id;
    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE UPDATE_REIMBURSEMENT(
    re_id IN ERS_REIMBURSEMENTS.R_ID%TYPE,
    resolvedTime IN ERS_REIMBURSEMENTS.R_RESOLVED%TYPE,
    r_resolver IN ERS_REIMBURSEMENTS.U_ID_RESOLVER%TYPE,
    r_status IN ERS_REIMBURSEMENTS.RT_STATUS%TYPE)
    
IS
BEGIN
    UPDATE ERS_REIMBURSEMENTS SET R_RESOLVED=resolvedTime, U_ID_RESOLVER=r_resolver,RT_STATUS=r_status
                              WHERE R_ID=re_id;
                    
    COMMIT;
END;
/
--Create the Managers    
CALL ADD_USER('pm', 'eyepatch', 'President', 'Morty', 'EvilMorty@Citadel.com', 1);
CALL ADD_USER('rs', 'wubbalubbadubdub', 'Rick', 'Sanchez', 'RickestRick@Citadel.com', 1);
CALL ADD_USER('mm', 'lookatme', 'Mister', 'Meeseeks', 'ExistenceIsPain@Citadel.com', 1);

--Create the employees
CALL ADD_USER('js', 'pathetic', 'Jerry', 'Smith', 'ImJerrySmith@Citadel.com', 2);
CALL ADD_USER('bs', 'morecomplete', 'Beth', 'Smith', 'EquineSurgeon@Citadel.com', 2);
CALL ADD_USER('ss', 'summerissafe', 'Summer', 'Smith', 'KeepSummerSafe@Citadel.com', 2);
CALL ADD_USER('tinyrick', 'imtinyrick', 'Tiny', 'Rick', 'TinyRick@Citadel.com', 2);

