using Android.OS;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Runtime.ConstrainedExecution;
using System.Security.Cryptography.X509Certificates;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace GenericTestApp
{
    // Learn more about making custom code visible in the Xamarin.Forms previewer
    // by visiting https://aka.ms/xamarinforms-previewer
    [DesignTimeVisible(false)]
    public partial class MainPage : ContentPage
    {
        private string rightAnswer;
        private int rightAnswerCount = 0;
        private int quizCount = 1;
        static private int QUIZ_COUNT = 40;

        List<List<string>> quizArray = new List<List<string>>();

        string[][] quizData =
            {
            // {"Question", "Right Answer", "Choice1", "Choice2", "Choice3"}
            new string[] {"1", "Which role is responsible for carrying out the activities of a process?", "Process practitioner", "Process owner", "Change manager", "Service manager", "", "", "", "", "", "", "", "", "", "", "", ""},
            new string[] {"1", "Which process or function is responsible for monitoring activities and events in the IT infrastructure?", "IT operations management", "Service level management", "Capacity management", "Incident management", "", "", "", "", "", "", "", "", "", "", "", ""},
            new string[] {"1", "Which of the following options is a hierarchy that is used in knowledge management?", "Data – Information – Knowledge – Wisdom", "Wisdom – Information – Data – Knowledge", "Knowledge – Wisdom – Information – Data", "Information – Data – Knowledge – Wisdom", "", "", "", "", "", "", "", "", "", "", "", ""},
            new string[] {"1", "At which stage of the service lifecycle should the processes necessary to operate a new service be defined?", "Service design: Design the processes", "Service strategy: Develop the offerings", "Service transition: Plan and prepare for deployment", "Service operation: IT operations management", "", "", "", "", "", "", "", "", "", "", "", ""},
            new string[] {"1", "Why are public frameworks, such as ITIL, attractive when compared to proprietary knowledge?", "Proprietary knowledge may be difficult to adopt", "replicate or transfer since it is often undocumented", "Public frameworks are always cheaper to adopt", "Public frameworks are prescriptive and tell you exactly what to do", "Proprietary knowledge has been tested in a wide range of environments", "", "", "", "", "", "", "", "", "", ""},
            new string[] {"1", "Which of the following is an objective of business relationship management?", "To ensure high levels of customer satisfaction", "To identify patterns of business activity", "To secure funding to manage the provision of services", "To ensure strategic plans for IT services exist", "", "", "", "", "", "", "", "", "", "", "", ""},
            new string[] {"1", "The design of IT services requires the effective and efficient use of the four Ps. What are these four Ps?", "People, process, products, partners", "People, process, partners, performance", "Performance, process, products, plans", "People, products, plans, partners", "", "", "", "", "", "", "", "", "", "", "", ""},
            new string[] {"1", "Which of the following BEST describes service strategies value to the business?", "Enabling the service provider to have a clear understanding of what levels of service will make their customers successful", "Allows higher volumes of successful change", "Reduction in unplanned costs through optimized handling of service outages", "Reduction in the duration and frequency of service outages", "", "", "", "", "", "", "", "", "", "", "", ""},
            new string[] {"1", "Which two processes will contribute MOST to enabling effective problem detection?", "Incident and event management", "Incident and financial management", "Change and release and deployment management", "Knowledge and service level management", "", "", "", "", "", "", "", "", "", "", "", ""},
            new string[] {"1", "Which of the following would be used to communicate a high level description of a major change that involved significant cost and risk to the organization?", "Change proposal", "Change policy", "Service request", "Risk register", "", "", "", "", "", "", "", "", "", "", "", ""},
            };

        protected override void onCreate(Bundle savedInstanceState)
        {
            // Recieve quizCategory from StartActivity.
            int quizCategory;

            // Create quizArray from quizData.
            for (int i = 0; i < quizData.Length; i++)
            {
                // Prepare array.
                List<string> tmpArray = new List<string>(quizData[i]);
                tmpArray.Add(quizData[i][0]); // QuizCategory
                tmpArray.Add(quizData[i][1]); // Question
                tmpArray.Add(quizData[i][2]); // Right Answer
                tmpArray.Add(quizData[i][3]); // Choice1
                tmpArray.Add(quizData[i][4]); // Choice2
                tmpArray.Add(quizData[i][5]); // Choice3

                quizArray.Add(tmpArray);
            }

            showNextQuiz();
        }

        public void showNextQuiz()
        {
            // Update quixCountLabel.
            countLabel.Text = string.Format("Q" + quizCount);

            // Generate random number between 0 and quiz size - 1.
            Random random = new Random();
            int randomNum = random.Next(quizArray.Count - 1);

            // Pick one quiz set.
            List<string> quiz = quizArray[randomNum];

            // Set question adn right answer.
            //Array format.
            questionLabel.Text= string.Format(quiz[1]);
            rightAnswer = quiz[2];

            // Remove question from quiz and shuffle choices.
            quiz.RemoveAt(0);
            quiz.RemoveAt(1);
            quiz.Shuffle();

            // Remove this quiz from quizArray
            quizArray.RemoveAt(randomNum);
        }

        public void checkAnswer(object sender, System.EventArgs e)
        {
            string btnText = (sender as Button).Text;

            string alertTitle;

            if (btnText.Equals(rightAnswer))
            {
                // Correct!
                alertTitle = "Correct!";
                rightAnswerCount++;
            }

            else
            {
                // Wrong ...
                alertTitle = "Wrong...";
            }

            DisplayAlert(alertTitle, "Answer : " + rightAnswer, "OK");

            if (quizCount == QUIZ_COUNT)
            { 
                
            }

            throw new NotImplementedException();

        }
       
    
    }

    
    public MainPage()
    {
        InitializeComponent();
    }
   
}
public static class ThreadSafeRandom
{
    [ThreadStatic] private static Random Local;

    public static Random ThisThreadsRandom
    {
        get { return Local ?? (Local = new Random(unchecked(Environment.TickCount * 31 + Thread.CurrentThread.ManagedThreadId))); }
    }
}

static class MyExtensions
{
    public static void Shuffle<T>(this IList<T> list)
    {
        int n = list.Count;
        while (n > 1)
        {
            n--;
            int k = ThreadSafeRandom.ThisThreadsRandom.Next(n + 1);
            T value = list[k];
            list[k] = list[n];
            list[n] = value;
        }
    }
}