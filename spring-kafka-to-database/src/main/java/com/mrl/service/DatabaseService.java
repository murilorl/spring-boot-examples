package com.mrl.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class DatabaseService {

    private final DatabaseConnection connection;

    public int insert(String recordMetadata, String recordValue) {

        try {
            PreparedStatement pstmt = connection.connection().prepareStatement(
                    "INSERT INTO JSON_PLACEHOLDER_USERS(RECORD_METADATA, RECORD_VALUE) SELECT TO_VARIANT(?), TO_VARIANT(?)");
            pstmt.setString(1, recordMetadata);
            pstmt.setString(2, recordValue);

            int count = pstmt.executeUpdate();
            return count;

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }

        return 0;
    }
}
