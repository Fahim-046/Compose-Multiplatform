#!/bin/bash

if [ "YES" = "$OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED" ]; then
  echo "Skipping Gradle build task invocation due to OVERRIDE_KOTLIN_BUILD_IDE_SUPPORTED environment variable set to \"YES\""
  exit 0
fi

cd "$SRCROOT/.."
./gradlew :composeApp:embedAndSignAppleFrameworkForXcode

echo "Building Kotlin framework for $CONFIGURATION-$PLATFORM_NAME"
./gradlew :composeApp:embedAndSignAppleFrameworkForXcode \
    -Pxcode.configuration=$CONFIGURATION \
    -Pxcode.platform=$PLATFORM_NAME \
    --stacktrace
