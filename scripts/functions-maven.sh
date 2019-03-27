#!/bin/bash

DEFAULT_POM_FILE="pom.xml"

OS=osx
WINDOWS_UBUNTU_CONFIG_FILE="/etc/wsl.conf"

if [ "$(uname)" == "Darwin" ]; then
	OS=osx
elif [[ "$(expr substr $(uname -s) 1 5)" == "Linux" && "$(uname -a | grep -q Microsoft && echo "true")" != "true" ]]; then
    OS=linux
elif [[ "$(expr substr $(uname -s) 1 5)" == "Linux" && "$(uname -a | grep -q Microsoft && echo "true")" == "true" ]]; then
    OS=windowsbash
    if [[ ! -f "$WINDOWS_UBUNTU_CONFIG_FILE" ]]; then
        echo "Please create $WINDOWS_UBUNTU_CONFIG_FILE using following guide"
        echo "https://nickjanetakis.com/blog/setting-up-docker-for-windows-and-wsl-to-work-flawlessly#ensure-volume-mounts-work"
        exit 1
    fi
elif [ "$(expr substr $(uname -s) 1 10)" == "MINGW32_NT" ]; then
    OS=windows
    echo "Please use Ubuntu Bash"
    exit 1
elif [ "$(expr substr $(uname -s) 1 10)" == "MINGW64_NT" ]; then
    OS=windows
    echo "Please use Ubuntu Bash"
    exit 1
fi

IFCONFIG="$(which ifconfig 2>/dev/null)"
if [[ "$IFCONFIG" == "" && "$OS" == "windows" ]]; then
	LOCAL_IP="$(ipconfig | grep "(Default Switch)" -A 6 | grep "IPv4 Address" | head -n1 | awk -F ": " '/1/ {print $2}')"
else
	if [[ "$IFCONFIG" == "" ]]; then
		LOCAL_IP="localhost"
	else
	    if [[ "$OS" == "windowsbash" ]]; then
            LOCAL_IP="$(ifconfig | grep 'inet ' | grep -v '127.0.0.1' | awk '{print $2}' | head -1)"
	    else
            LOCAL_IP="$(ifconfig $(route -n get 0.0.0.0 2 | grep 'interface' | awk '{print $2}') | grep 'inet ' | grep -v '127.0.0.1' | awk '{print $2}')"
		fi
	fi
fi

function fixPath() {
	local CHECK_PATH="${1?Need path}"
	if [[ "$OS" == "windows" ]]; then
		echo $(cygpath -wa $CHECK_PATH)
	elif [[ "$OS" == "windowsbash" ]]; then
#		local NEWPATH="$(wslpath -w $CHECK_PATH)"
		echo "${CHECK_PATH//\/mnt/}"
	else
		echo "$CHECK_PATH"
	fi
}

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

function evalMaven() {
    local PARAM=${1:-}
    echo $(mvn help:evaluate -q -DforceStdout -D"expression=$PARAM")

}



AEM_USER=$(getParamOrDefault "$SCRIPT_PARAMS" "crx.password" "$POM_FILE")
AEM_PASS=$(getParamOrDefault "$SCRIPT_PARAMS" "crx.username" "$POM_FILE")
AEM_HOST=$(getParamOrDefault "$SCRIPT_PARAMS" "crx.host" "$POM_FILE")
AEM_PORT=$(getParamOrDefault "$SCRIPT_PARAMS" "crx.port" "$POM_FILE")
AEM_SCHEMA=$(getParamOrDefault "$SCRIPT_PARAMS" "package.uploadProtocol" "$POM_FILE")
SELENIUMHUB_HOST=$(getParamOrDefault "$SCRIPT_PARAMS" "seleniumhubhost.host" "$POM_FILE")
SELENIUMHUB_PORT=$(getParamOrDefault "$SCRIPT_PARAMS" "seleniumhubhost.port" "$POM_FILE")
SELENIUMHUB_SCHEME=$(getParamOrDefault "$SCRIPT_PARAMS" "seleniumhubhost.scheme" "$POM_FILE")
SELENIUMHUB_SERVICE=$(getParamOrDefault "$SCRIPT_PARAMS" "seleniumhubhost.service" "$POM_FILE")


if [ -z "$SKIP_PRINT_CONFIG" ]; then
    echo "Params:     $SCRIPT_PARAMS"
    echo "POM_FILE:   ${POM_FILE:-$DEFAULT_POM_FILE}"
    echo "AEM_USER:   $AEM_USER"
    echo "AEM_PASS:   $(sed "s/\w/*/g" <<< ${AEM_PASS})"
    echo "AEM_HOST:   $AEM_HOST"
    echo "AEM_PORT:   $AEM_PORT"
    echo "AEM_SCHEMA: $AEM_SCHEMA"
    echo "SELENIUMHUB_HOST: $SELENIUMHUB_HOST"
    echo "SELENIUMHUB_PORT: $SELENIUMHUB_PORT"
    echo "SELENIUMHUB_SCHEME: $SELENIUMHUB_SCHEME"
    echo "SELENIUMHUB_SERVICE: $SELENIUMHUB_SERVICE"

    unset SKIP_PRINT_CONFIG
fi