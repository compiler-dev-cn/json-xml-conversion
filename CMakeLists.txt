project(json-xml-conversion)
cmake_minimum_required(VERSION 3.4.1)
enable_language(C CXX)

include_directories(lex)

add_executable(test lex/xml_lexer_test.c)