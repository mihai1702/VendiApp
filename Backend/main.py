from starlette.staticfiles import StaticFiles

BACKEND_URL = "http://10.0.2.2:8000"  # Pentru emulator Android

from typing import List

from fastapi import FastAPI

from database_connection import connect_to_db

app = FastAPI()

app.mount("/images", StaticFiles(directory="images"), name="images")

connect_to_db()
@app.get("/")
async def root():
    return {"message": "Hello World"}


@app.get("/hello/{name}")
async def say_hello(name: str):
    return {"message": f"Hello {name}"}

def get_all_listings():
    try:
        connection = connect_to_db()
        if connection:
            cursor = connection.cursor()
            query = "SELECT * FROM listings"
            cursor.execute(query)
            rows = cursor.fetchall()

            listings = []
            for row in rows:
                if row[4]:
                    image_url = f"{BACKEND_URL}/images/{row[4]}"
                else:
                    image_url = None
                listing = {
                    'id': row[0],
                    'title': row[1],
                    'description': row[2],
                    'price': row[3],
                    'photolink': image_url,
                    'phoneNumber':row[5],
                    'sellerName': row[6],
                    'location': row[7]
                }
                listings.append(listing)

            return listings
        else:
            print("Conexiunea la baza de date a eșuat.")
            return []
    finally:
        if connection:
            connection.close()

@app.get("/listings", response_model=List[dict])
async def read_listings():
    listings = get_all_listings()
    return listings