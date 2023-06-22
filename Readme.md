# Skin Allergy Detection Android App
This repository contains the source code for an Android app that uses a skin allergy classification model deployed on Hugging Face's cloud as an API. The app also includes an SQLite database for storing the user's history of allergy detections.

# Project Overview
Skin allergies can be a serious problem for many people, and early detection is important to prevent further complications. This Android app aims to help users detect skin allergies quickly and easily by taking a picture of the affected area and using a machine learning model to classify the allergy type.

The app uses Hugging Face's cloud to host the allergy classification model, which is accessed through an API. The user can take a picture of the affected area, and the app sends the image to the API for classification. The API returns the predicted allergy type, which is displayed to the user.

The app also includes a history feature, where the user's previous allergy detections are stored in an SQLite database. This allows the user to keep track of their allergies over time and monitor any changes or patterns.

# Getting Started
To use this app, you will need to have Android Studio installed on your computer. You can download Android Studio from the official website: https://developer.android.com/studio

Once you have Android Studio installed, you can clone this repository and open the project in Android Studio. From there, you can build and run the app on an Android device or emulator.

The app uses Hugging Face's cloud to host the allergy classification model, which is accessed through the following API endpoint: https://huggingface.co/spaces/BilalSardar/Skin-Diseases-Classification. The user can take a picture of the affected area, and the app sends the image to the API for classification. The API returns the predicted allergy type, which is displayed to the user. The app is currently set up to connect to a specific API endpoint, so you will need to update the endpoint URL in the code to match your own deployment.

# Contributing
Contributions to this project are welcome! If you find a bug or have a suggestion for a new feature, please open an issue on the GitHub repository.

If you would like to contribute code, please fork the repository and submit a pull request with your changes. Please make sure to follow the project's coding style and guidelines.

