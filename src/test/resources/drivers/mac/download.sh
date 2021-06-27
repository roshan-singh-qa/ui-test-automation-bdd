#!/bin/bash
echo "Downloading chromedriver"
curl -o chromedriver.zip "https://chromedriver.storage.googleapis.com/83.0.4103.39/chromedriver_mac64.zip"
echo "Unzipping chromedriver"
unzip chromedriver.zip
echo "Removing chromedriver.zip"
rm -f chromedriver.zip