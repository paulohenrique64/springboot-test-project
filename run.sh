#!/bin/bash
firefox ./frontend/index.html &
./backend/mvnw -f ./backend/pom.xml spring-boot:run

