#!/bin/bash

P='\033[0;37m'
L='\033[0;36m'
J='\033[0;35m'
RED='\033[0;31m'
GREEN='\033[0;32m'

  printf "${L}This Script run ours test, the test are listed like 'Running test nº1'${NC}\n"
  printf "${L}If below a test you see text on ${RED}RED${L} that means the test fail${NC}\n"
  printf "${L}You will see the text on ${RED}RED${L} because this test detected sintax erros${L}${NC}\n"
  printf "${J}The suite test will start in 2 seconds${NC}\n"
  sleep 2
  printf "${P}\n"
  printf "    ▀ ▀ ▀\n"
  printf " ▀  ▀    ▀\n"
  printf "    ▀    ▀\n"
  printf " ▀  ▀ ▀ ▀\n"
  printf "    ▀\n"
  printf "    ▀\n"
  printf "\n"
  printf "\n"

for i in {1..9}
do
  printf "${GREEN}Running test nº${i}${NC}\n"
  printf "${RED}"
  java -jar dist/Compiler.jar test/wrong/wrongTest${i}.plum
  printf "${NC}"
done
