import random
from datetime import date
from datetime import timedelta

# User data
fnames = ['Bob', 'Terry', 'Sarah', 'Jenny']
lnames = ['Smith', 'Prime', 'Conner', 'Baker']
emails = ['b.smith@email.com', 't.prime@email.com', 's.conner@email.com', 'j.baker@email.com']
passwords = ['$2a$10$P.QxV9.QTpxpmoZavv3So.Rd6jg9eu7ZfPmaC9v0Xeo2WqKBrc6z2', '$2a$10$P.QxV9.QTpxpmoZavv3So.Rd6jg9eu7ZfPmaC9v0Xeo2WqKBrc6z2', '$2a$10$P.QxV9.QTpxpmoZavv3So.Rd6jg9eu7ZfPmaC9v0Xeo2WqKBrc6z2', '$2a$10$P.QxV9.QTpxpmoZavv3So.Rd6jg9eu7ZfPmaC9v0Xeo2WqKBrc6z2']
roles = ['USER', 'USER', 'USER', 'USER']
verified = 1

# Category data
cnames = ['groceries', 'gasoline', 'rent', 'reimbursment']
descriptions = ['Food is important', 'Need to be able to go vroom', 'Gotta live somewhere', 'Woohoo!']
type = ['DEPOSIT', 'DEPOSIT', 'DEPOSIT', 'WITHDRAWAL']

# SQL file var
file = None

def openFile(filename):
    global file
    file = open(filename, 'w')

def closeFile():
    file.close()

# Write the data for the 4 users
def writeUsers():

    for i in range(0, 4):
        file.write(f'INSERT INTO user (userid, f_name, l_name, email, password, role, verified) VALUES ({i}, \'{fnames[i]}\', \'{lnames[i]}\', \'{emails[i]}\', \'{passwords[i]}\', \'{roles[i]}\', {verified});\n')

# Write the data for the 4 categories
def writeCategories():
    for i in range(0, 4):
        file.write(f'INSERT INTO category (categoryid, description, name, type) VALUES ({i}, \'{descriptions[i]}\', \'{cnames[i]}\', \'{type[i]}\');\n')

# Write random data for 1000 withdrawal transactions
def writeWithdrawals():
    for i in range(0, 1000):
        userid = random.randint(0, 3)
        catid = random.randint(0, 2)
        amount = round(random.uniform(100.0, 1200.0), 2)
        tDate = date.today() - timedelta(days= random.randint(0,100))

        file.write(f'INSERT INTO withdrawal (withdrawalid, user_userid, category_categoryid, amount, date) VALUES ({i}, \'{userid}\', \'{catid}\', \'{amount}\', \'{tDate}\');\n')


# Write random data for 300 deposites transactions
def writeDeposits():
    for i in range(0, 300):
        userid = random.randint(0, 3)
        catid = 3
        amount = round(random.uniform(100.0, 500.0), 2)
        tDate = date.today() - timedelta(days= random.randint(0,100))

        file.write(f'INSERT INTO deposit (depositid, user_userid, category_categoryid, amount, date) VALUES ({i}, \'{userid}\', \'{catid}\', \'{amount}\', \'{tDate}\');\n')

# Write the commit
def writeCommit():
    file.write('COMMIT;')

# Execute steps
filename = 'genData.sql'

openFile(filename)

writeUsers()
writeCategories()
writeWithdrawals()
writeDeposits()
writeCommit()

closeFile()