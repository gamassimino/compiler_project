#!/bin/bash
RED='\033[0;31m'
if [ $# != 1 ]; then
    echo "Usage: :plum <file>"
else
    plum_file="$1"
    if [ -e program.s ]; then
        gcc -o print print.c program.s && ./print
        
        rm print
        rm program.s
        exit 0
    else
        printf "${RED}The file ${plum_file} doesn't exist ${NC}\n"
        exit 1
    fi
fi