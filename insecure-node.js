const { exec } = require("child_process");

// 🚨 Vulnerability: Hardcoded Secret
const API_KEY = "my-secret-api-key"; 

// 🚨 Vulnerability: Command Injection
const userInput = process.argv[2]; // User input from CLI
exec(`ls ${userInput}`, (error, stdout, stderr) => {
    if (error) {
        console.error(`Execution error: ${error}`);
        return;
    }
    console.log(stdout);
});
