package iu.LCAC.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class JsonManager {

  private File jsonFile;
  private JsonObject jsonObject;
  private Gson gson;

  /**
   * コンストラクタ
   *
   * @param json_file JSONファイル
   */
  public JsonManager(File json_file) {
    this.jsonFile = json_file;
    this.gson = new GsonBuilder().setPrettyPrinting().create();
    this.jsonObject = loadJsonObject();
  }

  public JsonManager(String json_file_path){
    this(new File(json_file_path));
  }

  /* **** Public Methods **** */

  /**
   * JSONファイルを書き出す
   *
   * @return 成功した場合true
   */
  public boolean writeoutJson() {
    // 存在しない場合は作る
    boolean file_creation_rslt = createAFileAfterConfirmation(this.jsonFile.getAbsolutePath());

    if (file_creation_rslt) {
      // 書き込み
      try (Writer writer =
          new OutputStreamWriter(
              new FileOutputStream(jsonFile), StandardCharsets.UTF_8)) {
        gson.toJson(jsonObject, writer);
        return true;
      } catch (IOException e) {
        e.printStackTrace();
        return false;
      }
    } else {
      return false;
    }
  }

  public File getJsonFile() {
    return jsonFile;
  }

  /**
   * パス形式のキーで値を取得する
   * 例: "reference_cohort_and_imaging/dataset_name/Answer"
   * 例: "reference_cohort_and_imaging/dataset_name/Page\\/Line" (スラッシュをエスケープ)
   *
   * @param path_key スラッシュ区切りのパス
   * @return 値（文字列）、見つからない場合はnull
   */
  public String getValue(String path_key) {
    // エスケープされたスラッシュを一時的にプレースホルダーに置き換える
    String placeholder = "\u0000ESCAPED_SLASH\u0000";
    path_key = path_key.replace("\\/", placeholder);

    String[] keys = path_key.split("/");
    JsonElement current = jsonObject;

    for (String key : keys) {
      // プレースホルダーを元のスラッシュに戻す
      key = key.replace(placeholder, "/");
      // エスケープされたスペースを通常のスペースに戻す
      key = key.replace("\\ ", " ");

      if (current == null || !current.isJsonObject()) {
        return null;
      }
      current = current.getAsJsonObject().get(key);
    }

    if (current != null && current.isJsonPrimitive()) {
      return current.getAsString();
    }
    return null;
  }

  /**
   * パス形式のキーで値を設定する
   *
   * @param path_key スラッシュ区切りのパス
   * @param value 設定する値
   */
  public void setValue(String path_key, String value) {
    // エスケープされたスラッシュを一時的にプレースホルダーに置き換える
    String placeholder = "\u0000ESCAPED_SLASH\u0000";
    path_key = path_key.replace("\\/", placeholder);

    String[] keys = path_key.split("/");
    JsonObject current = jsonObject;

    for (int i = 0; i < keys.length - 1; i++) {
      String key = keys[i].replace(placeholder, "/").replace("\\ ", " ");

      if (!current.has(key)) {
        current.add(key, new JsonObject());
      }
      JsonElement element = current.get(key);
      if (element.isJsonObject()) {
        current = element.getAsJsonObject();
      } else {
        // 既存の値がオブジェクトでない場合、新しいオブジェクトで置き換え
        JsonObject newObj = new JsonObject();
        current.add(key, newObj);
        current = newObj;
      }
    }

    String lastKey = keys[keys.length - 1].replace(placeholder, "/").replace("\\ ", " ");
    current.addProperty(lastKey, value);
  }

  public JsonObject getJsonObject() {
    return this.jsonObject;
  }

  /* **** Private Method **** */

  /**
   * JSONファイルを読み込んでJsonObjectを作成する
   *
   * @return JsonObject
   */
  private JsonObject loadJsonObject() {
    if (jsonObject == null) {
      if (jsonFile.isFile()) {
        // Objectはまだないが、Fileはある時
        try (Reader reader =
            new InputStreamReader(
                new FileInputStream(jsonFile), StandardCharsets.UTF_8)) {
          JsonElement element = JsonParser.parseReader(reader);
          if (element.isJsonObject()) {
            jsonObject = element.getAsJsonObject();
          } else {
            jsonObject = new JsonObject();
          }
        } catch (FileNotFoundException fnfe) {
          fnfe.printStackTrace();
          jsonObject = new JsonObject();
        } catch (IOException ioe) {
          ioe.printStackTrace();
          jsonObject = new JsonObject();
        }
        return jsonObject;
      } else {
        // JsonObjectもなく、JsonFileもないとき
        jsonObject = new JsonObject();
        return jsonObject;
      }
    }
    return jsonObject;
  }

  /**
   * ファイルが存在しない場合は作成する
   *
   * @param json_file_path_str ファイルパス
   * @return 成功した場合true
   */
  private static boolean createAFileAfterConfirmation(String json_file_path_str) {
    File json_file = new File(json_file_path_str);
    if (!json_file.exists()) {
      boolean creation_rslt = false;
      json_file.getParentFile().mkdirs();
      try {
        creation_rslt = json_file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
        return false;
      }
      if (!creation_rslt) {
        return false;
      }
    }
    return true;
  }

  /* **** Test Method **** */

  public static void main(String[] args) {
    // 基本的な使い方
    /** 書き込みテスト */
    JsonManager jm = new JsonManager(new File("/tmp/test.json"));
    jm.setValue("test/key1", "value1");
    jm.setValue("test/key2", "value2");
    jm.writeoutJson();

    /** 読み込みテスト */
    JsonManager jm2 = new JsonManager(new File("/tmp/test.json"));
    System.out.println("key1 = " + jm2.getValue("test/key1"));
    System.out.println("key2 = " + jm2.getValue("test/key2"));
  }
}