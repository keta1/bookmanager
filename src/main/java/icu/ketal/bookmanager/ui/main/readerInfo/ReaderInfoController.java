package icu.ketal.bookmanager.ui.main.readerInfo;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import icu.ketal.bookmanager.dao.ReaderDao;
import icu.ketal.bookmanager.dao.impl.ReaderDaoImpl;
import icu.ketal.bookmanager.entry.Reader;
import icu.ketal.bookmanager.ui.components.KDatePicker;
import icu.ketal.bookmanager.util.DialogBuilder;
import io.datafx.controller.ViewController;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

@ViewController(value = "/fxml/ui/reader/ReadInfo.fxml")
public class ReaderInfoController {

    private final ReaderDao readerDao = new ReaderDaoImpl();
    @FXML
    private JFXTreeTableView<ReaderInfo> treeTableView;
    @FXML
    private JFXTreeTableColumn<ReaderInfo, Integer> id;
    @FXML
    private JFXTreeTableColumn<ReaderInfo, String> name;
    @FXML
    private JFXTreeTableColumn<ReaderInfo, String> sex;
    @FXML
    private JFXTreeTableColumn<ReaderInfo, Integer> age;
    @FXML
    private JFXTreeTableColumn<ReaderInfo, String> IDNum;
    @FXML
    private JFXTreeTableColumn<ReaderInfo, String> effectiveDate;
    @FXML
    private JFXTreeTableColumn<ReaderInfo, Integer> maxToBorrow;
    @FXML
    private JFXTreeTableColumn<ReaderInfo, Long> phoneNumber;
    @FXML
    private JFXTreeTableColumn<ReaderInfo, Double> deposit;
    @FXML
    private JFXTreeTableColumn<ReaderInfo, String> certificateType;
    @FXML
    private JFXTreeTableColumn<ReaderInfo, String> job;
    @FXML
    private JFXTreeTableColumn<ReaderInfo, String> issueDate;

    @PostConstruct
    public void init() throws Exception {
        setupEditableTableView();
    }

    private <T> void setupCellValueFactory(JFXTreeTableColumn<ReaderInfo, T> column, Function<ReaderInfo, ObservableValue<T>> mapper) {
        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<ReaderInfo, T> param) -> {
            if (column.validateValue(param)) {
                return mapper.apply(param.getValue().getValue());
            } else {
                return column.getComputedValue(param);
            }
        });
    }

    private void setupEditableTableView() {
        setupCellValueFactory(id, p -> p.id.asObject());
        setupCellValueFactory(name, ReaderInfo::nameProperty);
        setupCellValueFactory(sex, ReaderInfo::sexProperty);
        setupCellValueFactory(age, p -> p.age.asObject());
        setupCellValueFactory(IDNum, ReaderInfo::IDNumProperty);
        setupCellValueFactory(effectiveDate, ReaderInfo::effectiveDateProperty);
        setupCellValueFactory(maxToBorrow, p -> p.maxToBorrow.asObject());
        setupCellValueFactory(phoneNumber, p -> p.phoneNumber.asObject());
        setupCellValueFactory(deposit, p -> p.deposit.asObject());
        setupCellValueFactory(certificateType, ReaderInfo::certificateTypeProperty);
        setupCellValueFactory(job, ReaderInfo::jobProperty);
        setupCellValueFactory(issueDate, ReaderInfo::issueDateProperty);

        reload();
        treeTableView.setShowRoot(false);
    }

    private ObservableList<ReaderInfo> selectAll() {
        final ObservableList<ReaderInfo> dummy = FXCollections.observableArrayList();
        readerDao.selectAll()
                .stream()
                .map(i -> {
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    var effectiveDate = LocalDateTime
                            .ofInstant(
                                    java.time.Instant.ofEpochMilli(i.getEffectiveDate()),
                                    java.time.ZoneId.of("Asia/Kolkata")
                            ).format(fmt);
                    var issueDate = LocalDateTime
                            .ofInstant(
                                    java.time.Instant.ofEpochMilli(i.getIssueDate()),
                                    java.time.ZoneId.of("Asia/Kolkata")
                            ).format(fmt);
                    return new ReaderInfo(
                            i.getId(),
                            i.getName(),
                            i.isSex() ? "男" : "女",
                            i.getAge(),
                            i.getIDNum(),
                            effectiveDate,
                            i.getMaxToBorrow(),
                            i.getPhoneNumber(),
                            i.getDeposit(),
                            i.getCertificateType(),
                            i.getJob(),
                            issueDate
                    );
                })
                .forEach(dummy::add);
        return dummy;
    }

    private void reload() {
        ObservableList<ReaderInfo> dummyData = selectAll();
        treeTableView.setRoot(new RecursiveTreeItem<>(dummyData, RecursiveTreeObject::getChildren));
    }

    @FXML
    private void onNew() throws IOException {
        showModifyDialog(new Reader(), true);
    }

    @FXML
    private void onModify() throws IOException {
        var select = treeTableView.getSelectionModel().selectedItemProperty().get();
        if (select == null) {
            new DialogBuilder<>(treeTableView.getScene().getWindow())
                    .setTitle("提示")
                    .setMessage("请选择一个读者再进行修改")
                    .setOkButtonText("确定")
                    .build().show();
            return;
        }
        var index = select.getValue();
        showModifyDialog(readerDao.selectById(index.id.getValue()), false);
    }

    private void onModify(Reader reader) throws IOException {
        showModifyDialog(reader, false);
    }

    private void showModifyDialog(Reader reader, boolean add) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ui/reader/ModifyReaderInfo.fxml"));
        loader.setController(new InputController(reader, add));
        VBox view = loader.load();
        var layout = new JFXDialogLayout();
        layout.setBody(view);
        new DialogBuilder<>(treeTableView.getScene().getWindow())
                .setContent(layout)
                .build()
                .show();
    }

    static final class ReaderInfo extends RecursiveTreeObject<ReaderInfo> {
        final SimpleIntegerProperty id;
        final StringProperty name;
        final StringProperty sex;
        final SimpleIntegerProperty age;
        final StringProperty IDNum;
        final StringProperty effectiveDate;
        final SimpleIntegerProperty maxToBorrow;
        final SimpleLongProperty phoneNumber;
        final SimpleDoubleProperty deposit;
        final StringProperty certificateType;
        final StringProperty job;
        final StringProperty issueDate;

        ReaderInfo(int id, String name, String sex, int age, String IDNum, String effectiveDate, int maxToBorrow, long phoneNumber, double deposit, String certificateType, String job, String issueDate) {
            this.id = new SimpleIntegerProperty(id);
            this.name = new SimpleStringProperty(name);
            this.sex = new SimpleStringProperty(sex);
            this.age = new SimpleIntegerProperty(age);
            this.IDNum = new SimpleStringProperty(IDNum);
            this.effectiveDate = new SimpleStringProperty(effectiveDate);
            this.maxToBorrow = new SimpleIntegerProperty(maxToBorrow);
            this.phoneNumber = new SimpleLongProperty(phoneNumber);
            this.deposit = new SimpleDoubleProperty(deposit);
            this.certificateType = new SimpleStringProperty(certificateType);
            this.job = new SimpleStringProperty(job);
            this.issueDate = new SimpleStringProperty(issueDate);
        }

        StringProperty nameProperty() {
            return name;
        }

        StringProperty sexProperty() {
            return sex;
        }

        StringProperty IDNumProperty() {
            return IDNum;
        }

        StringProperty effectiveDateProperty() {
            return effectiveDate;
        }

        StringProperty certificateTypeProperty() {
            return certificateType;
        }

        StringProperty jobProperty() {
            return job;
        }

        StringProperty issueDateProperty() {
            return issueDate;
        }
    }

    public final class InputController {
        private final Reader reader;
        private final boolean add;
        @FXML
        private Label title;
        @FXML
        private JFXTextField id;
        @FXML
        private JFXTextField name;
        @FXML
        private JFXTextField sex;
        @FXML
        private JFXTextField age;
        @FXML
        private JFXTextField IDNum;
        @FXML
        private KDatePicker effectiveDate;
        @FXML
        private JFXTextField maxToBorrow;
        @FXML
        private JFXTextField phoneNumber;
        @FXML
        private JFXTextField deposit;
        @FXML
        private JFXTextField certificateType;
        @FXML
        private JFXTextField job;
        @FXML
        private KDatePicker issueDate;

        public InputController(Reader reader, boolean add) {
            this.reader = reader;
            this.add = add;
        }

        @FXML
        public void initialize() {
            if (add) {
                title.setText("添加读者信息");
                id.setDisable(false);
            }
            id.setText("" + reader.getId());
            name.setText(reader.getName());
            sex.setText(reader.isSex() ? "男" : "女");
            age.setText(String.valueOf(reader.getAge()));
            IDNum.setText(reader.getIDNum());
            effectiveDate.setValue(LocalDateTime
                    .ofInstant(java.time.Instant.ofEpochMilli(reader.getEffectiveDate()),
                            java.time.ZoneId.of("Asia/Kolkata")).toLocalDate());
            maxToBorrow.setText("" + reader.getMaxToBorrow());
            phoneNumber.setText(String.valueOf(reader.getPhoneNumber()));
            deposit.setText("" + reader.getDeposit());
            certificateType.setText(reader.getCertificateType());
            job.setText(reader.getJob());
            issueDate.setValue(LocalDateTime
                    .ofInstant(java.time.Instant.ofEpochMilli(reader.getIssueDate()),
                            java.time.ZoneId.of("Asia/Kolkata")).toLocalDate());
        }

        @FXML
        private void onClick() {
            String msg;
            try {
                reader.setId(Integer.parseInt(id.getText()));
                reader.setName(name.getText());
                reader.setSex(sex.getText().equals("男"));
                reader.setAge(Integer.parseInt(age.getText()));
                reader.setIDNum(IDNum.getText());
                var effective = Timestamp.valueOf(effectiveDate.getValue().atStartOfDay()).getTime();
                reader.setEffectiveDate(effective);
                reader.setMaxToBorrow(Integer.parseInt(maxToBorrow.getText()));
                reader.setPhoneNumber(Long.parseLong(phoneNumber.getText()));
                reader.setDeposit(Double.parseDouble(deposit.getText()));
                reader.setCertificateType(certificateType.getText());
                reader.setJob(job.getText());
                var issue = Timestamp.valueOf(issueDate.getValue().atStartOfDay()).getTime();
                reader.setIssueDate(issue);
                readerDao.replace(reader);
                id.getScene().getWindow().hide();
                reload();
                msg = "修改成功";
            } catch (Exception e) {
                e.printStackTrace();
                msg = "修改失败";
            }
            var snackbar = new JFXSnackbar((Pane) treeTableView.getScene().getRoot());
            snackbar.setPrefWidth(300);
            snackbar.fireEvent(new JFXSnackbar.SnackbarEvent(
                    new JFXSnackbarLayout(msg, "", null),
                    Duration.millis(3000), null));
        }
    }
}
