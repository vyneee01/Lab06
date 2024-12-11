package com.example.lab06; // Khai báo package của ứng dụng.

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge; // Thêm khả năng hiển thị full màn hình.
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adapter.SanPhamAdapter;
import com.example.model.DanhMuc;
import com.example.model.SanPham;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity { // MainActivity kế thừa từ AppCompatActivity để tạo giao diện chính của ứng dụng.
    Spinner spnDanhMuc; // Biến tham chiếu đến Spinner để chọn danh mục sản phẩm.
    ArrayAdapter<DanhMuc> danhMucAdapter; // Adapter để kết nối dữ liệu danh mục với Spinner.
    ArrayAdapter<SanPham> sanPhamAdapter; // Adapter để kết nối dữ liệu sản phẩm với ListView.
    EditText editmaSP, edittenSp, editgiaSp; // Biến tham chiếu đến các EditText để nhập thông tin sản phẩm.
    ListView lvSanpham; // Biến tham chiếu đến ListView hiển thị danh sách sản phẩm.
    Button btnThem; // Biến tham chiếu đến nút thêm sản phẩm.
    DanhMuc selectedDanhMuc = null; // Biến lưu danh mục đang được chọn.

    @Override
    protected void onCreate(Bundle savedInstanceState) { // Gọi khi Activity được tạo.
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this); // Hiển thị màn hình full màn hình, loại bỏ các khoảng đệm mặc định.

        setContentView(R.layout.activity_main); // Gán layout XML (activity_main) làm giao diện chính.

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> { // Lắng nghe sự thay đổi các vùng khoảng đệm của màn hình.
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom); // Thiết lập padding phù hợp.
            return insets;
        });

        addView(); // Gọi hàm khởi tạo các view và thiết lập giao diện.
        addEvent(); // Gọi hàm thiết lập các sự kiện cho giao diện.
    }
    private SanPhamAdapter sanPhamAAdapter;

    private void addEvent() { // Hàm thiết lập các sự kiện tương tác cho giao diện.
        spnDanhMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Lấy danh mục được chọn từ Spinner
                selectedDanhMuc = danhMucAdapter.getItem(position);
                // Xóa danh sách sản phẩm hiện tại trong ListView.
                sanPhamAdapter.clear();
                // Thêm các sản phẩm tương ứng với danh mục đã chọn.
                sanPhamAdapter.addAll(selectedDanhMuc.getSanPhams());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xulyNhapSanPham();
            }
        });
    }

    private void xulyNhapSanPham() {
        SanPham sanPham = new SanPham(
                editmaSP.getText().toString(),
                edittenSp.getText().toString(),
                Integer.parseInt(editgiaSp.getText().toString())
        );

        if (selectedDanhMuc != null) {
            selectedDanhMuc.getSanPhams().add(sanPham);
            sanPhamAdapter.add(sanPham); // Thêm sản phẩm vào adapter
            sanPhamAdapter.notifyDataSetChanged(); // Cập nhật giao diện
        } else {
            Toast.makeText(this, "Vui lòng chọn danh mục trước khi thêm sản phẩm", Toast.LENGTH_SHORT).show();
        }
    }


    private void addView() { // Hàm khởi tạo các view và thiết lập các adapter.
        // Lấy tham chiếu đến Spinner từ giao diện XML.
        spnDanhMuc = findViewById(R.id.spnSanpham);

        // Khởi tạo Adapter cho danh mục sản phẩm và gán vào Spinner.
        danhMucAdapter = new ArrayAdapter<DanhMuc>(MainActivity.this, android.R.layout.simple_spinner_item);
        spnDanhMuc.setAdapter(danhMucAdapter);
        // Lấy tham chiếu đến nút Thêm từ giao diện XML.
        lvSanpham = findViewById(R.id.lvSanpham);
        // Khởi tạo Adapter cho danh sách sản phẩm và gán vào ListView.
        sanPhamAdapter = new SanPhamAdapter(MainActivity.this, new ArrayList<>());
        lvSanpham.setAdapter(sanPhamAdapter);

        // Lấy tham chiếu đến các EditText từ giao diện XML.
        editmaSP = findViewById(R.id.editmaSp);
        edittenSp = findViewById(R.id.edittenSp);
        editgiaSp = findViewById(R.id.editgiaSp);

        // Lấy tham chiếu đến nút Thêm từ giao diện XML.
        btnThem = findViewById(R.id.btnThem);

        danhMucAdapter.add(new DanhMuc("1", "Áo"));
        danhMucAdapter.add(new DanhMuc("2", "Quần"));
        danhMucAdapter.add(new DanhMuc("3", "Giày"));
    }
}
