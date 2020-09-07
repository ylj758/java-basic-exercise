import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        String firstWordList = "";
        String secondWordList = "";

        Scanner sc = new Scanner(System.in);
        firstWordList = sc.nextLine();
        secondWordList = sc.next();

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
        //按要求输出到命令行
        result.forEach(element->{System.out.println(element);});
    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        String combineStr = firstWordList.concat(secondWordList);
        if(combineStr.contains(",,")){
            throw new RuntimeException("input not valid");
        }
        List<String> combineStrList = Arrays.asList(combineStr.split(""));
        combineStrList.forEach(element->{
            char temp = element.charAt(0);
            if(temp!=',' && (temp>'Z'||temp<'A') && (temp>'z'||temp<'a')){
                throw new RuntimeException("input not valid");
            }
        });

        firstWordList = firstWordList.toUpperCase();
        String secondWord = secondWordList.toUpperCase();
        //一.找到共有字符串，并去重排序
        List<String> commonResultList = new ArrayList<>();
        List<String> firstList = Arrays.asList(firstWordList.split(","));
        firstList.forEach(element->{
            if(secondWord.contains(element)){
                commonResultList.add(element);
            }
        });
        List<String> commonSortResult = new ArrayList<>();
        commonSortResult = commonResultList.stream().distinct().sorted().collect(Collectors.toList());
        //二.组装成字符串内部各字符间加空格
        List<String> commonResultSortWithSpace = new ArrayList<>();
        commonSortResult.forEach(element -> commonResultSortWithSpace.add(String.join(" ",element.split(""))));
        return commonResultSortWithSpace;
    }
}