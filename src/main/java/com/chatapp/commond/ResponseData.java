package com.chatapp.commond;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Đối tượng chứa dữ liệu phản hồi từ một yêu cầu.
 *
 * @param <T>     Kiểu dữ liệu của dữ liệu phản hồi.
 * @param status  Mã trạng thái của phản hồi.
 * @param message Thông điệp kèm theo phản hồi.
 * @param data    Dữ liệu phản hồi.
 * @Data: Tạo các phương thức getter, setter, equals, hashCode và toString tự
 *        động.
 * @AllArgsConstructor: Tạo một constructor có tham số cho tất cả các trường.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData<T> {
    @JsonIgnore
    private HttpStatus status;
    @JsonProperty("status")
    private int statusCode;
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private T data;
    public ResponseData(HttpStatus status, String message, T data) {
        this.data = data;
        this.status = status;
        this.message = message;
        this.statusCode = status.value();
    }
}
