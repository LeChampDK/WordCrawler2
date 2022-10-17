using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using WordCounterDemo.Models;

namespace WordCounterDemo
{
    public class CrawlerLogic
    {
        private readonly char[] sep = " \\\n\t\"$'!,?;.:-_**+=)([]{}<>/@&%€#".ToCharArray();

        private Dictionary<string, int> words = new Dictionary<string, int>();
        private List<BEDocument> documents = new List<BEDocument>();

        public Dictionary<string, int> GetWords()
        {
            return words;
        }

        public List<BEDocument> GetDocuments()
        {
            return documents;
        }

        // Returns a list of all words from a file
        private ISet<string> ExtractWordsInFile(FileInfo f)
        {
            var res = new HashSet<string>();
            var content = File.ReadAllLines(f.FullName);
            foreach (var line in content)
            {
                foreach (var aWord in line.Split(sep, StringSplitOptions.RemoveEmptyEntries))
                {
                    res.Add(aWord);
                }
            }

            return res;
        }

        public void CrawlSequentially(DirectoryInfo dir, List<string> extensions)
        {
            Console.WriteLine("Crawling " + dir.Name);

            foreach (var file in dir.EnumerateFiles())
            {
                BEDocument newDoc = new BEDocument
                {
                    mId = documents.Count + 1,
                    mUrl = file.FullName,
                    mIdxTime = DateTime.Now.ToString(),
                    mCreationTime = file.CreationTime.ToString()
                };
                documents.Add(newDoc);

                var wordsInFile = ExtractWordsInFile(file);

                foreach (var word in wordsInFile)
                {
                    if (!words.ContainsKey(word))
                    {
                        // Console.WriteLine($"{word} does not exist in the list, adding it with 1 count");
                        words.Add(word, 1);
                    }
                    else
                    {
                        // Console.WriteLine($"{word} is in the list, increment its count value");
                        words[word]++;
                    }
                }
            }
            foreach (var d in dir.EnumerateDirectories())
            {
                CrawlSequentially(d, extensions);
            }
        }

        public void CrawlParallel(DirectoryInfo dir, List<string> extensions)
        {
            throw new NotImplementedException();
        }
    }
}
