#!/bin/bash

DOCKER=$(/usr/bin/which docker)

if [[ "$OS" == "windowsbash" ]]; then
	DOCKER="docker.exe"
fi