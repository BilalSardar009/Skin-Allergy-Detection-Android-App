import requests
import time

def prediction(filedata):
    try:
        response = requests.post("https://bilalsardar-skin-diseases-classification.hf.space/run/predict", json={
         "data": [
           filedata,
        ]}).json()
        data = response["data"]
        return data[0]+"$"+data[1]+"$"+data[2]+"$"+data[3]+"$"+data[4]
    except requests.ConnectionError as e:
        print("OOPS!! Connection Error. Make sure you are connected to Internet. Technical Details given below.\n")
        print(str(e))
    except requests.Timeout as e:
        print("OOPS!! Timeout Error")
        print(str(e))
    except requests.RequestException as e:
        print("OOPS!! General Error")
        print(str(e))
    except KeyboardInterrupt:
        print("Someone closed the program")
    return "Cannot"