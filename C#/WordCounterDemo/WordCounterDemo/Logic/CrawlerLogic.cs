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

        // Returns a list of all words from a file
        private ISet<string> ExtractWordsInFile(FileInfo f)
        {
            char[] sep = " \\\n\t\"$'!,?;.:-_**+=)([]{}<>/@&%€#".ToCharArray();
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

        public void CrawlSeq(DirectoryInfo dir)
        {
            Dictionary<string, int> pWords = new Dictionary<string, int>();

            foreach (var d in dir.EnumerateDirectories("", SearchOption.AllDirectories))
            {
                foreach (var f in d.EnumerateFiles())
                {
                    var wordsInFile = ExtractWordsInFile(f);
                    foreach (var word in wordsInFile)
                    {
                        if (!pWords.ContainsKey(word))
                        {
                            // Console.WriteLine($"{word} does not exist in the list, adding it with 1 count");
                            pWords.Add(word, 1);
                        }
                        else
                        {
                            // Console.WriteLine($"{word} is in the list, increment its count value");
                            pWords[word]++;
                        }
                    }
                }
            }

            Console.WriteLine("Words crawled: " + pWords.Count());
        }

        public void CrawlPar(DirectoryInfo dir)
        {
            object Lock = new object();
            Dictionary<string, int> pWords = new Dictionary<string, int>();

            Parallel.ForEach(dir.EnumerateDirectories("", SearchOption.AllDirectories), d =>
            {
                Parallel.ForEach(d.EnumerateFiles(), f =>
                {
                    var wordsInFile = ExtractWordsInFile(f);
                    foreach (var word in wordsInFile)
                    {
                        lock (Lock)
                        {
                            if (!pWords.ContainsKey(word))
                            {
                                // Console.WriteLine($"{word} does not exist in the list, adding it with 1 count");
                                pWords.Add(word, 1);
                            }
                            else
                            {
                                // Console.WriteLine($"{word} is in the list, increment its count value");
                                pWords[word]++;
                            }
                        }
                    }
                });
            });

            Console.WriteLine("Words crawled: " + pWords.Count());
        }


        // Recursion will cause dictionary to reset (!)
        //public void CrawlParallel(DirectoryInfo dir, List<string> extensions)
        //{
        //    Dictionary<string, int> pWords = new Dictionary<string, int>();
        //    object Lock = new object();

        //    foreach (var file in dir.EnumerateFiles())
        //    {
        //        var wordsInFile = ExtractWordsInFile(file);
        //        foreach (var word in wordsInFile)
        //        {
        //            lock (Lock)
        //            {
        //                if (!pWords.ContainsKey(word))
        //                {
        //                    // Console.WriteLine($"{word} does not exist in the list, adding it with 1 count");
        //                    pWords.Add(word, 1);
        //                }
        //                else
        //                {
        //                    // Console.WriteLine($"{word} is in the list, increment its count value");
        //                    pWords[word]++;
        //                }
        //            }
        //        }
        //    }

        //    Parallel.ForEach(dir.EnumerateDirectories(), (dir, state, index) =>
        //    {
        //        CrawlParallel(dir, extensions);
        //    });
        //}

        //public void CrawlTestLinq(DirectoryInfo dir)
        //{
        //    object Lock = new object();
        //    Dictionary<string, int> pWords = new Dictionary<string, int>();

        //    var sets = dir
        //        .EnumerateDirectories("", SearchOption.AllDirectories)
        //        .AsParallel()
        //        .SelectMany(d => d.EnumerateFiles())
        //        .Select(f => ExtractWordsInFile(f));

        //    Parallel.ForEach(sets, s =>
        //    {
        //        foreach (var word in f)
        //        {
        //            lock (Lock)
        //            {
        //                if (!pWords.ContainsKey(word))
        //                {
        //                    // Console.WriteLine($"{word} does not exist in the list, adding it with 1 count");
        //                    pWords.Add(word, 1);
        //                }
        //                else
        //                {
        //                    // Console.WriteLine($"{word} is in the list, increment its count value");
        //                    pWords[word]++;
        //                }
        //            }
        //        }
        //    });

        //    Console.WriteLine("Words crawled: " + pWords.Count());
        //}

        // Recursion will cause dictionary to reset (!)
        //public void CrawlTest2(DirectoryInfo dir)
        //{
        //    object Lock = new object();
        //    Dictionary<string, int> pWords = new Dictionary<string, int>();

        //    Parallel.ForEach(dir.EnumerateFiles(), f =>
        //    {
        //        var wordsInFile = ExtractWordsInFile(f);
        //        Parallel.ForEach(wordsInFile, word =>
        //        {
        //            lock (Lock)
        //            {
        //                if (!pWords.ContainsKey(word))
        //                {
        //                    // Console.WriteLine($"{word} does not exist in the list, adding it with 1 count");
        //                    pWords.Add(word, 1);
        //                }
        //                else
        //                {
        //                    // Console.WriteLine($"{word} is in the list, increment its count value");
        //                    pWords[word]++;
        //                }
        //            }
        //        });
        //    });
        //    Parallel.ForEach(dir.EnumerateDirectories(), d =>
        //    {
        //        CrawlTest2(d);
        //    });
        //}

    }
}
