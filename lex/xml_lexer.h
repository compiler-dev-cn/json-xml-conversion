//
// Created by imurluck on 2022/3/7.
//

#ifndef JSON_XML_CONVERSION_XML_LEXER_H
#define JSON_XML_CONVERSION_XML_LEXER_H

#endif //JSON_XML_CONVERSION_XML_LEXER_H

#include "stdio.h"


typedef enum {
    XML_PI_START_TAG = 1, XML_PI_END_TAG,
    XML_COMMENT_START_TAG, XML_COMMENT_END_TAG,
    XML_ELEMENT_START_TAG, XML_ELEMENT_EMPTY_END_TAG, XML_ELEMENT_END_START_TAG, XML_ELEMENT_END_TAG,
    XML_NAME,
    XML_ATTRIBUTE_VALUE, XML_EQ

} XmlTokenType;

const char* getXmlTokenTypeName(const XmlTokenType xmlTokenType) {
    switch (xmlTokenType) {
        case XML_PI_START_TAG: return "xml_pi_start_tag";
        case XML_PI_END_TAG: return "xml_pi_end_tag";
        case XML_ELEMENT_START_TAG: return "xml_element_start_tag";
        case XML_ELEMENT_EMPTY_END_TAG: return "xml_element_empty_end_tag";
        case XML_ELEMENT_END_START_TAG: return "xml_element_end_start_tag";
        case XML_ELEMENT_END_TAG: return "xml_element_end_tag";
        case XML_NAME: return "xml_name";
        case XML_ATTRIBUTE_VALUE: return "xml_attribute_value";
        case XML_EQ: return "xml_eq";

    }
    return "Unknown";
}


void reset(FILE* in);