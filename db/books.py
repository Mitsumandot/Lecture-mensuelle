

import pandas as pd
import requests


api = "https://www.googleapis.com/books/v1/volumes"



params = {
    "q" : f"intitle:Albert Camus",
    "maxResults": 40
}

author = "Albert Camus"

response = requests.get(api, params=params)

response = response.json()

for item in response["items"]:
    authors = item["volumeInfo"].get("authors", [])
    
    if any(a.lower() == author.lower() for a in authors):
        print(item["volumeInfo"]["title"])
    


