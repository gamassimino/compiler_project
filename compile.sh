#!/bin/bash

RED='\033[0;31m'
GREEN='\033[0;32m'
NC='\033[0m'
P='\033[0;35m'
L='\033[0;34m'
U='\033[0;36m'
M='\033[0;33m'

if [ -e src/Example/Parser.java ]; then
	rm src/Example/*
fi

printf "${GREEN}==${P}==${L}==${U}==${M}==${GREEN}==${P}==${L}==${U}==${M}==${GREEN}==${P}==${L}==${U}==${M}==${GREEN}==${P}==${L}==${GREEN}=${NC}\n"
printf "${GREEN}=== Hi this is the ${P}:P${L}l${U}u${M}m ${GREEN}compiler ===${NC}\n"
printf "${GREEN}= The compilation will start in 5s ==${NC}\n"
printf "${GREEN}==${P}==${L}==${U}==${M}==${GREEN}==${P}==${L}==${U}==${M}==${GREEN}==${P}==${L}==${U}==${M}==${GREEN}==${P}==${L}==${GREEN}=${NC}\n"
sleep 7
ant

if [ -e src/Example/Parser.java ]; then
	printf "\n"
	printf "\n"
	printf "\n"
	printf "\n"
	printf "\n"
	printf "\n"
	printf "\n"	
	printf "${GREEN}======================================${NC}\n"
	printf "${GREEN}=== Great, compilation was SUCCESS ===${NC}\n"
	printf "${GREEN}======================================${NC}\n"
	# ./simulator
	exit 0
else
	printf "\n"
	printf "\n"
	printf "\n"
	printf "\n"
	printf "\n"
	printf "\n"
	printf "\n"
	printf "${RED}=====================================${NC}\n"
	printf "${RED}=== I'm sorry, compilation FAILED ===${NC}\n"
	printf "${RED}=====================================${NC}\n"
	exit 1
fi
