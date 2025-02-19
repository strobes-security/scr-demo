#!/bin/bash

# Validate inputs
if [ "$#" -lt 1 ]; then
    echo "Usage: ./run_semgrep.sh <file_path>"
    exit 1
fi

FILE_PATH=$1
OUTPUT_FILE="semgrep_results.json"
RULES_DIR="/app/rules/javascript"  # Path to the rules directory in the container

# Scan the code using Semgrep with the rules directory (no need to specify language)
echo "Running Semgrep on $FILE_PATH with custom rules from $RULES_DIR"
semgrep --config=$RULES_DIR --output=$OUTPUT_FILE --json $FILE_PATH

# Notify the user
echo "Scan complete. Results saved in $OUTPUT_FILE"