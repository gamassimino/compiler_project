#!/bin/bash

P='\033[0;37m'
L='\033[0;36m'
J='\033[0;35m'
RED='\033[0;31m'
GREEN='\033[0;32m'

for i in {1..19}
do
	printf "${GREEN}Running test nº${i}${NC}\n"
	printf "${RED}"
  java -jar dist/Compiler.jar test/ceci/test${i}.plum
	printf "${NC}"
done
