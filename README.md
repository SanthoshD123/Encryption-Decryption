# Encryption-Decryption

A simple Java application for encrypting and decrypting files using AES encryption.

## Features

- Encrypt files using AES-128 encryption
- Decrypt previously encrypted files
- Command-line interface for easy interaction
- Automatic key generation and storage

## Prerequisites

- Java JDK 8 or higher
- Basic knowledge of file paths on your operating system

## Installation

1. Clone this repository:
   ```
   git clone https://github.com/SanthoshD123/Encryption-Decryption.git
   ```
2. Compile the Java file:
   ```
   javac FileEncryptor.java
   ```

## Usage

Run the program:
```
java FileEncryptor
```

The application provides a simple menu:
1. **Encrypt a file** - Select this option to encrypt a file
2. **Decrypt a file** - Select this option to decrypt a previously encrypted file
3. **Exit** - Exit the program

For both encryption and decryption, you'll need to provide:
- Input file path - The path to the file you want to encrypt/decrypt
- Output file path - Where you want to save the encrypted/decrypted file

## How It Works

- The program generates a 128-bit AES key when it starts
- The key is saved to a file named `encryption.key` in the current directory
- Files are processed in chunks of 4KB to support large files efficiently

## Security Notes

- The encryption key is stored in plaintext in the file system
- For production use, consider implementing secure key management
- This tool is intended for educational purposes and basic file security

## Contributing

Feel free to submit issues or pull requests if you'd like to improve this project.
