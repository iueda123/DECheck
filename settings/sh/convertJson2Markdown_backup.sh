#!/bin/bash

# ${1}: DE or QA. 入出力フォルダ名の制御のため
# ${2}: json file name

message=""
counter=1
for arg in "$@"; do
    message="${message}  * ARG${counter}: ${arg}"

    counter=$((counter + 1))
done

message="convertJson2Markdown ./json/${1}/${2} ./md/${1}/${2%.json}.md"

zenity --info --text="${message}"



