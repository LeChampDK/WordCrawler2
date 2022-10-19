using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WordCounterDemo
{
    public class App
    {
        public void Run()
        {
            string[] menu =
            {
                "Crawl sequentially",
                "Crawl parallel",
                "Exit",
            };

            var selection = ShowMenu(menu);

            while (selection != 3)
            {
                switch (selection)
                {
                    case 1:
                        handleCrawlSequentially();
                        break;
                    case 2:
                        handleCrawlParallel();
                        break;
                    case 3:
                        Console.WriteLine("Closing");
                        break;
                }

                selection = ShowMenu(menu);
            };

        }

        public int ShowMenu(string[] menuItems)
        {
            for (int i = 0; i < menuItems.Length; i++)
            {
                Console.WriteLine($"{i + 1}: {menuItems[i]}");
            }

            int selection;

            while (!int.TryParse(Console.ReadLine(), out selection)
                || selection < 1
                || selection > 3)
            {
                Console.WriteLine("Please input a number between 1-3");
            }

            return selection;
        }

        private void handleCrawlSequentially()
        {
            CrawlerLogic cl = new CrawlerLogic();
            var root = new DirectoryInfo(Config.DATASET);

            DateTime START = DateTime.Now;

            // Crawling sequentially
            cl.CrawlSequentially(root, new List<string> { ".txt" });

            // Crawling parallel
            // todo;

            TimeSpan TIME_USED = DateTime.Now - START;

            var res = cl.GetWords();
            var dirSize = DirSize(root);

            Console.WriteLine("Words crawled: " +  res.Count());
            Console.WriteLine("Dir size in mb: " + (dirSize / 1024f) / 1024f);
            Console.WriteLine("TIME USED: " + TIME_USED.TotalMilliseconds);
        }

        private void handleCrawlParallel()
        {
            CrawlerLogic cl = new CrawlerLogic();
            var root = new DirectoryInfo(Config.DATASET);

            DateTime START = DateTime.Now;

            // Crawling parallel
            cl.CrawlParallel(root, new List<string> { ".txt" });

            TimeSpan TIME_USED = DateTime.Now - START;
            var dirSize = DirSize(root);
            Console.WriteLine("Dir size in mb: " + (dirSize / 1024f) / 1024f);
            Console.WriteLine("TIME USED: " + TIME_USED.TotalMilliseconds);


        }

        public static long DirSize(DirectoryInfo d)
        {
            long size = 0;
            // Add file sizes.
            FileInfo[] fis = d.GetFiles();
            foreach (FileInfo fi in fis)
            {
                size += fi.Length;
            }
            // Add subdirectory sizes.
            DirectoryInfo[] dis = d.GetDirectories();
            foreach (DirectoryInfo di in dis)
            {
                size += DirSize(di);
            }
            return size;
        }
    }
}
