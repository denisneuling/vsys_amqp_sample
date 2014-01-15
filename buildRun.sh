#!/bin/bash

mvn clean install && chmod +x target/showcase.jar && clear && ./target/showcase.jar
