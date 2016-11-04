#!/bin/bash
RED='\033[0;31m'
if [ $# != 1 ]; then
    echo "Usage: :plumc <file.plum>"
else 
  plum_file="$1"
  printf "${RED}"
  java -jar dist/Compiler.jar ${plum_file}
  printf "${NC}"
fi