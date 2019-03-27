#!/bin/bash

DOCKER=$(/usr/bin/which docker &>/dev/null)

if [[ "$OS" == "windowsbash" ]]; then
	DOCKER="docker.exe"
fi