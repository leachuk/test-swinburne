#!/bin/bash

DEFAULT_POM_FILE="pom.xml"

function getDefaultFromPom() {
    local PARAM_NAME=${1:-}
    local POM_FILE=${2:-$DEFAULT_POM_FILE}
    echo $(cat $POM_FILE | grep "<${PARAM_NAME}>" | sed -e 's/.*>\(.*\)<.*/\1/')

}

function getParamOrDefault() {
    local PARAMS=${1:-}
    local PARAM_NAME=${2:-}
    local POM_FILE=${3:-$DEFAULT_POM_FILE}
    local DEFAULT_VALUE=$(getDefaultFromPom "${PARAM_NAME}" "${POM_FILE}")

    if [[ "" == "$DEFAULT_VALUE" ]]; then
        echo DEFAULT MISSING IN POM
    else
        PARAMS_CHECK=$(echo ${PARAMS} | grep "${PARAM_NAME}=")
        if [[ "" == "$PARAMS_CHECK" ]]; then
            echo $DEFAULT_VALUE
        else
            echo $(echo ${PARAMS_CHECK} | sed -e "s/.*${PARAM_NAME}=\(.*\).*/\1/")
        fi
    fi
}
