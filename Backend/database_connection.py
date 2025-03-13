import mysql.connector
from mysql.connector import Error

def connect_to_db():
    try:
        connection = mysql.connector.connect(
            host='localhost',  # Adresa serverului MySQL
            user='root',       # Numele utilizatorului
            password='0000',  # Parola utilizatorului
            database='VendiApp'  # Numele bazei de date
        )

        if connection.is_connected():
            print("Conectat la MySQL")
            return connection
    except Error as e:
        print(f"Error: {e}")
        return None

# Testarea conexiunii
conn = connect_to_db()
if conn:
    conn.close()
