import mysql.connector
from mysql.connector import Error

def connect_to_db():
    try:
        connection = mysql.connector.connect(
            host='localhost',
            user='root',
            password='0000',
            database='VendiApp'
        )

        if connection.is_connected():
            print("Conectat la MySQL")
            return connection
    except Error as e:
        print(f"Eroare: {e}")
        return None

conn = connect_to_db()
if conn:
    conn.close()

def add_car_to_db(title, description, price, photoLink, phoneNumber, sellerName, location):
    connection = None
    cursor = None
    try:
        connection = connect_to_db()  # Se face conexiunea
        if connection is None:
            print("Conectarea a eșuat.")
            return

        cursor = connection.cursor()
        query = """
            INSERT INTO listings (title, description, price, photolink, phoneNumber, sellerName, location)
            VALUES (%s, %s, %s, %s, %s, %s, %s)
        """
        values = (title, description, price, photoLink, phoneNumber, sellerName, location)
        cursor.execute(query, values)
        connection.commit()

        print("Anunțul a fost adăugat cu succes.")
    except Error as e:
        print(f"Eroare la adăugarea anunțului: {e}")
    finally:
        if cursor:
            cursor.close()
        if connection:
            connection.close()