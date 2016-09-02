#!/bin/bash

RED='\033[0;31m'
GREEN='\033[0;32m'

for i in {1..32}
do
	printf "${GREEN}Running test nº${i}${NC}\n"
	printf "${RED}"
  java -jar dist/Compiler.jar test/test${i}.plum
	printf "${NC}"
done
