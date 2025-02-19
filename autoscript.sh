#!/bin/bash

# Validate inputs
if [ "$#" -lt 1 ]; then
    echo "Usage: ./run_semgrep.sh <file_path>"
    exit 1
fi

FILE_PATH=$1
OUTPUT_FILE="semgrep_results.json"

# Run Semgrep with default registry rules (auto) without specifying language
echo "Running Semgrep on $FILE_PATH using default registry rules"
semgrep --config=auto --output=$OUTPUT_FILE --json $FILE_PATH

# Notify the user
echo "Scan complete. Results saved in $OUTPUT_FILE"
