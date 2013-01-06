start database manager
create database ibs
list database directory
connect to ibs

create table ploginInfo(userName varchar(20),password varchar(20),lastloginDate date,lastloginTime time,blockstatus int,userID int,defaultAccount bigint,transactionPass varchar(20))
insert into ploginInfo values('karanjain','ajaykr','20.9.2008','20:08:56',0,1,1024231332,'thesaurus')
list tables
create table cloginInfo(userName varchar(20),password varchar(20),lastloginDate date,lastloginTime time,blockstatus int,userID int,defaultAccount bigint,transactionPass varchar(20))
insert into cloginInfo values('gg','rohyt','20.9.2008','20:08:56',0,1,1024231332,'thesaurus')
create table aloginInfo(userName varchar(20),password varchar(20),lastloginDate date,lastloginTime time,blockstatus int,userID int,defaultAccount bigint,transactionPass varchar(20))
insert into aloginInfo values('karanjain','ajaykr','20.9.2008','20:08:56',0,1,1024231332,'thesaurus')

create table myProfile(userID int,myName varchar(25),myAddress varchar(50),myPhone varchar(15),mail varchar(30),IDproof varchar(20))
insert into myProfile values(1,'Karan Jain','vishwasaraya r# 45','+91-9936759359','karan.jain@gmail.com','123')

create table accountMapping (userID int,accountNumber bigint)
insert into accountMapping values(1,123342)

create table profileSettings(userID int,securityCode varchar(20),defaultTheme varchar(20))
insert into profileSettings values(1,'enabled','BLUE')

create table providerInfo(billerType varchar(20),billNumber varchar(20),amountDue float,dateIssue date,lastDate date,penalityAmount float,status varchar(20))
insert into providerInfo values('BSNL','ACT2345754',400.00,'12.10.2008','31.10.2008',20.00,'Pending')

create table myFavorites(userID int,favTaskID int,myUsage varchar(30))
insert into myFavorites values(1,34343	,'pata nahi kya hai')

create table billerMapping(userID int,provider varchar(80),nick varchar (20))
insert into billerMapping values(1,'BSNL INDIA','BSNL')

create table billInfo(accountNumber bigint,provider varchar(80),billNumber bigint,amountPaid float,dateTransaction date,timeTransaction time,TransactionID int)
insert into billInfo values(123342,'12dsa',123456,2000.00,null,null,0)
insert into billInfo values(897,'123dsa',123457,2000.00,null,null,0)


create table chequeBook(accountNumber bigint,chequeQty int,dateIssued date,chequeBookNumber int,chequeStart int,chequeEnd int)
insert into chequeBook values(123342,2,'12.03.2003',212343,001230,232301)

create table letterCredit(accountNumber bigint,InFavourOf varchar(30),amount float,description varchar(160),ValidDate date)
insert into letterCredit values(123342,'registrar',1231.00,'please assign me this for MBA fees','12.4.2009')

create table loanDetails(loanType varchar(20),ROI float,loanDesc varchar(200))
insert into loanDetails values('house',12.34,'taken for 5 years nobodys home')

create table standingInstruction(accountFrom bigint,accountTo bigint,typeSI varchar(20),descSI varchar(200),timeServed time,dateServed date,amount float,timeScope time)
insert into standingInstruction values(123342,232133,'asd','transfer some money','12:23:30','12.3.2009',123.23,'12:23:34')

create table remittance(accountIssueFrom bigint,accountIssueTo bigint,amount float,dateIssue date,transactionID int,bankIssueTo varchar(50))
insert into remittance values(232122,123342,231.23,'12.3.2008',123,'gg')

create table accountDetails(accountNumber bigint,accountType varchar(20),openDate date,balance float,holder1 varchar(20),holder2 varchar(20),holder3 varchar(20),gurantee1 varchar(20),gurantee2 varchar(20),IDproof1 varchar(20),IDproof2 varchar(20),IDproof3 varchar(20),draftLimit float)
insert into accountDetails values(123342,'personal','12.3.2008',123000.00,'karan','ajay','gg','rohyt','aka','123','345','567',50000.00)

create table credit(accountNumber bigint,amount float,transactionID int,details varchar(80))
insert into credit values( 123342,30000.00,434322,'transfered to account number 12334')
insert into credit values(12334,4000,213125,'transfered to account number 123342')

create table debit(accountNumber bigint,amount float,transactionID int,details varchar(80))
insert into debit values(123342,40000.00,213125,'debited by account number 12334')
insert into debit values(12334,30000.00,434322,'debited by account number 123342')

create table loanMapping(userID int,loanAccount bigint)
insert into loanMapping values(1,123342)

create table loanInfo(loanAccount bigint,loanType varchar(20),issuedAmount float,EMI float,timeIssue time,OverDue float,mortgageStuff varchar(100))
insert into loanInfo values(32443543,'car loan',20000.00,20.9,'12:04:03',1233.00,'land,house')

create table transaction(transactionID int,time time,date date)
insert into transaction values(434322,'02:23:43','2.5.2008')
insert into transaction values(213125,'08:08:26','9.9.2008')

create table Draft(acNumber bigint,favour varchar(100),amount float,payable varchar(100),status varchar(100),mode varchar(10),draftNum int)
insert into Draft values(123342,'registrar',1500.00,'SBI varanasi','pending','courier',456789)

create table count(cnt varchar(40),value int)
insert into count values('transactionID',0)
insert into count values('draftID',0)